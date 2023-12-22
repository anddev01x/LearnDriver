package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.R
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.FragmentWrongQuestionPracticeBinding
import com.example.learndriver.ui.activity.QuestionActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class WrongQuestionPracticeFragment : BaseFragment<FragmentWrongQuestionPracticeBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongQuestionPracticeBinding {
        return FragmentWrongQuestionPracticeBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        setUpViewPage()
    }

    private fun setUpViewPage() {

    }
}
