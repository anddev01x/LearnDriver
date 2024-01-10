package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.learndriver.databinding.FragmentExtentionBinding

class ExtensionFragment : BaseFragment<FragmentExtentionBinding>() {


    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExtentionBinding {
        return FragmentExtentionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        binding.layoutSupport.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Xin chao toi co the giup gi cho ban",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onClick(view: View) {

    }
}

