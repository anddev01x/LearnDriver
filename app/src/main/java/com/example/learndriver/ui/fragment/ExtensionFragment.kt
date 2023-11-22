package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learndriver.databinding.FragmentExtentionBinding

class ExtensionFragment : BaseFragment<FragmentExtentionBinding>() {


    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExtentionBinding {
        return FragmentExtentionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {

    }

    override fun onClick(view: View) {

    }
}

