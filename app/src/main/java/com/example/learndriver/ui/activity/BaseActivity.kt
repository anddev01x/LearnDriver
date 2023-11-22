package com.example.learndriver.ui.activity

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.learndriver.R

abstract class BaseAct<T : ViewBinding> : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: T
//    protected val binding:T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding.root)
        initViews()
    }

    protected abstract fun initViews()

    protected abstract fun initViewBinding(): T

    final override fun onClick(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        clickViews(view)
    }

    protected open fun clickViews(view: View) {
        // do nothing
    }
}