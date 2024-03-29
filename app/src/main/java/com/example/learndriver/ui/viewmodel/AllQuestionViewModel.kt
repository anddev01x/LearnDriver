package com.example.learndriver.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learndriver.api.Api
import com.example.learndriver.data_local.database.DatabaseSingleton
import com.example.learndriver.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllQuestionViewModel(application: Application) : AndroidViewModel(application) {
    private var listQuestion: MutableList<Question> = mutableListOf()
    private var listNotStudyQuestions: List<Question> = listOf()
    private var listAllQuestion: List<Question> = listOf()
    private var listWrongQuestion: List<Question> = listOf()
    private var listRandomQuestion: List<Question> = listOf()
    private var listFetchRandom: List<Question> = listOf()
    val answersMap: HashMap<String, String> = hashMapOf()

    @SuppressLint("StaticFieldLeak")
    private val mListAllQuestionMutableLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    private val mListNotStudyQuestionLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    private val mListWrongQuestionLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    private val mListRandomQuestionLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    private val _countdownTime = MutableLiveData<Long>()
    val countdownTime: LiveData<Long> get() = _countdownTime

    val listAllQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListAllQuestionMutableLiveData
    val listNotStudyQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListNotStudyQuestionLiveData

    val listWrongQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListWrongQuestionLiveData

    val listRandomQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListRandomQuestionLiveData


    init {
        getAllQuestion()
//        getNotStudyQuestions()
        getWrongQuestions()
    }

    fun updateAnswer(id: String, currentAnswer: String) {
        viewModelScope.launch {
            DatabaseSingleton.getDatabase(getApplication()).questionDao()
                .updateCurrentAnswer(id, currentAnswer)
        }
        answersMap[id] = currentAnswer
    }

    fun loadDataFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(200)
                val response = Api.apiService.getListQuestion()
                if (response.isSuccessful) {
                    val responseListAllQuestion = response.body()
                    if (responseListAllQuestion != null) {
                        listQuestion = responseListAllQuestion.question as MutableList<Question>
                        listQuestion.forEach {
                            DatabaseSingleton.getDatabase(getApplication()).questionDao()
                                .insertQuestions(it)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("loadDataFromAPI", "Error : ${e.message}", e)
            }
        }
    }

    private fun getAllQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAllQuestion =
                    DatabaseSingleton.getDatabase(getApplication()).questionDao().getAllQuestion()
                Log.i(this.toString(), "getAllQuestion: ${listAllQuestion.size}")

                withContext(Dispatchers.Main) {
                    listAllQuestion.associate { it.id to (it.currentAnswer ?: "") }.let {
                        answersMap.putAll(it)
                    }
                    listAllQuestionLiveData.postValue(listAllQuestion)
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error in getAllQuestion: ${e.message}", e)
            }
        }
    }

    fun getNotStudyQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            val listAllQuestion =
                DatabaseSingleton.getDatabase(getApplication()).questionDao().getAllQuestion()
            Log.i(this.toString(), "getAllQuestion: ${listAllQuestion.size}")
            listNotStudyQuestions = listAllQuestion.filter { it.currentAnswer == null }
            listNotStudyQuestionLiveData.postValue(listNotStudyQuestions)
        }
    }

    private fun getWrongQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val listAllQuestion =
                DatabaseSingleton.getDatabase(getApplication()).questionDao().getAllQuestion()
            listWrongQuestion =
                listAllQuestion.filter { it.currentAnswer != it.answer && !it.currentAnswer.isNullOrBlank() }
            listWrongQuestionLiveData.postValue(listWrongQuestion)
            Log.i(this.toString(), "listWrongQuestion: ${listWrongQuestion.size}")
        }
    }

    fun getNotStudyQuestion(): Int {
        return listNotStudyQuestions.size
    }

    fun getWrongQuestion(): Int {
        return listWrongQuestion.size
    }

    suspend fun getQuestionBetweenIds(startId: String, endId: String): List<Question> {
        return withContext(Dispatchers.IO) {
            DatabaseSingleton.getDatabase(getApplication()).questionDao()
                .getQuestionBetweenIds(startId, endId)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getRandomQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dao = DatabaseSingleton.getDatabase(getApplication()).questionDao()
                listRandomQuestion = dao.getRandomQuestions()
                listFetchRandom = listRandomQuestion
//                val questionIds = listFetchRandom.map { it.id }
                //get ra va set null ok roi
                listFetchRandom.forEach { it.currentAnswer = null }
                listRandomQuestionLiveData.postValue(listFetchRandom)
                Log.i("getRandomQuestion", "getRandomQuestion: $listFetchRandom")

            } catch (e: Exception) {
                Log.e("getRandomQuestion", "Error getting random questions", e)
            }
        }
    }

    suspend fun getSpecificQuestions(): List<Question> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext DatabaseSingleton.getDatabase(getApplication())
                    .questionDao().getSpecificQuestions()
            } catch (e: Exception) {
                Log.e("getSpecificQuestion", "Error getting specific questions", e)
                return@withContext emptyList()
            }
        }
    }

    fun updateAnswerExam(id: String, currentAnswer: String) {
        val targetQuestion = listFetchRandom.find { it.id == id }
        targetQuestion?.currentAnswer = currentAnswer
        answersMap[id] = currentAnswer
        Log.i("updateAnswerExam", "updateAnswerExam: $targetQuestion")
    }

    fun setCountdownTime(timeInMillis: Long) {
        _countdownTime.value = timeInMillis
    }

    fun getListResultExam(): List<Question> {
        return listRandomQuestion
    }

    fun getListTrueQuestionExam(): List<Question> {
        return listRandomQuestion.filter { it.answer == it.currentAnswer }
    }

    fun getListFalseQuestionExam(): List<Question> {
        return listRandomQuestion.filter {
            it.answer != it.currentAnswer
                    && it.currentAnswer != null
        }
    }

    fun getListWarningQuestionExam(): List<Question> {
        return listRandomQuestion.filter {
            it.currentAnswer == null
        }
    }
}