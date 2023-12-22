package com.example.learndriver.ui.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentExamTestTwoBinding
import com.example.learndriver.ui.activity.ExamTestActivity

class ExamTestTwoFragment : BaseFragment<FragmentExamTestTwoBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExamTestTwoBinding {
        return FragmentExamTestTwoBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {

    }


}