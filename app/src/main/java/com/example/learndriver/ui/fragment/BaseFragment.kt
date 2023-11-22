package com.example.learndriver.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.learndriver.R

abstract class BaseFragment<T : ViewBinding> : Fragment(), View.OnClickListener {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    protected abstract fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    protected abstract fun initViews()

    override fun onClick(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))
        clickViews(view)
    }

    protected open fun clickViews(view: View) {
        // do nothing
    }
}