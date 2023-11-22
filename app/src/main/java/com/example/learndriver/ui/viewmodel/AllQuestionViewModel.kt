package com.example.learndriver.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learndriver.api.Api
import com.example.learndriver.data_local.database.DatabaseSingleton
import com.example.learndriver.model.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AllQuestionViewModel : ViewModel() {
    private lateinit var listQuestion: MutableList<Question>
    private lateinit var listAllQuestion: List<Question>
    private lateinit var listNotStudyQuestion: MutableList<Question>

    @SuppressLint("StaticFieldLeak")
    private val context: Context? = null
    private val appDatabase = context?.let { DatabaseSingleton.getDatabase(it) }

    private val mListAllQuestionMutableLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    private val mListNotStudyQuestionLiveData: MutableLiveData<List<Question>?> =
        MutableLiveData()

    val listAllQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListAllQuestionMutableLiveData

    val listNotStudyQuestionLiveData: MutableLiveData<List<Question>?>
        get() = mListNotStudyQuestionLiveData

    init {
        initViews()
    }

    private fun initViews() {
        listQuestion = mutableListOf()
        listAllQuestion = listOf()
        listNotStudyQuestion = mutableListOf()
        viewModelScope.launch {
            try {
                delay(1500)
                val response = Api.apiService.getListQuestion()
                if (response.isSuccessful) {
                    val responseListAllQuestion = response.body()
                    if (responseListAllQuestion != null) {
                        listAllQuestion = responseListAllQuestion.question
                        listAllQuestionLiveData.value = listAllQuestion
                    } else {
                        mListAllQuestionMutableLiveData.value = null
                    }
                }
            } catch (e: Exception) {
                mListAllQuestionMutableLiveData.value = null
                Log.e("initViews", "Error : ${e.message}", e)
            }
        }
    }
//
//    fun loadDataFromAPI() {
//        viewModelScope.launch {
//            try {
//                delay(500)
//                val response = Api.apiService.getListQuestion()
//                if (response.isSuccessful) {
//                    val responseListAllQuestion = response.body()
//                    if (responseListAllQuestion != null) {
//                        listQuestion = responseListAllQuestion.question as MutableList<Question>
////                        if(appDatabase == null){
////                            Log.i("loadDataFromAPI", "loadDataFromAPI: NULLLLL")
////                        }
//                        appDatabase!!.questionDao().insertQuestions(listQuestion)
//                        listAllQuestionLiveData.value = listQuestion
//                    } else {
//                        mListAllQuestionMutableLiveData.value = null
//                    }
//                }
//            } catch (e: Exception) {
//                mListAllQuestionMutableLiveData.value = null
//                Log.e("initViews", "Error : ${e.message}", e)
//            }
//        }
//    }

    fun addNotStudyYet(question: Question) {
        listNotStudyQuestion = ArrayList(listAllQuestion)
        listNotStudyQuestion.remove(question)
        mListNotStudyQuestionLiveData.value = listNotStudyQuestion
    }

}