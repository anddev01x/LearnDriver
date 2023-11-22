package com.example.learndriver.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentNotStudyBinding

class NotStudyFragment : BaseFragment<FragmentNotStudyBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotStudyBinding {
        return FragmentNotStudyBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {

    }

}