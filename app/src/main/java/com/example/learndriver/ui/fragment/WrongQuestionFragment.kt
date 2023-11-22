package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.learndriver.databinding.FragmentWrongQuestionBinding


class WrongQuestionFragment : BaseFragment<FragmentWrongQuestionBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongQuestionBinding {
        return FragmentWrongQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {

    }
}