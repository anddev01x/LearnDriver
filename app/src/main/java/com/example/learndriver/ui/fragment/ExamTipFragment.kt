package com.example.learndriver.ui.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.learndriver.databinding.FragmentExamTipBinding
import com.example.learndriver.ui.activity.MainActivity

class ExamTipFragment : BaseFragment<FragmentExamTipBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExamTipBinding {
        return FragmentExamTipBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        binding.icBack.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}