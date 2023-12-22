package com.example.learndriver.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentExamTipBinding
import com.example.learndriver.ui.activity.MainActivity

class ExamTipFragment : BaseFragment<FragmentExamTipBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExamTipBinding {
        return FragmentExamTipBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun initViews() {
        val resourceId = R.string.tv_detail_web
        val text = getString(resourceId)

        val spannableString = SpannableString(text)
        spannableString.setSpan(UnderlineSpan(), 10, spannableString.length, 0)

        binding.tvDetailWeb.text = spannableString
        binding.icBack.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        binding.tvDetailWeb.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)
        if (R.id.tv_detail_web == view.id)
            return startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://hocthilaixe.com/")))
    }
}