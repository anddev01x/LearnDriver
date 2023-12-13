package com.example.learndriver.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.learndriver.R
import com.example.learndriver.databinding.ActivityPracticeBinding
import com.example.learndriver.ui.fragment.ExamTipFragment
import com.example.learndriver.ui.fragment.FilterQuestionFragment
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class PracticeActivity : BaseAct<ActivityPracticeBinding>() {
    private val allQuestionViewModel: AllQuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
    }

    override fun initViewBinding(): ActivityPracticeBinding {
        return ActivityPracticeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        getDataAndReplaceFragment()
    }

    private fun getDataAndReplaceFragment() {
        val receivedList = intent.getIntegerArrayListExtra("list_practice")
        receivedList?.let {
            for (selectedId in receivedList) {
                when (selectedId) {
                    R.id.ic_exam_topic -> replaceFragment(FilterQuestionFragment())
                    R.id.ic_idea -> replaceFragment(ExamTipFragment())
                    R.id.ic_wrong_question -> replaceFragment(FilterQuestionFragment())
                    R.id.ic_filter_question -> replaceFragment(FilterQuestionFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}