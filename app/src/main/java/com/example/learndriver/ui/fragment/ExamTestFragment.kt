package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentExamTestBinding
import com.example.learndriver.ui.activity.ExamTestActivity

class ExamTestFragment : BaseFragment<FragmentExamTestBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentExamTestBinding {
        return FragmentExamTestBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        binding.icRandom.setOnClickListener(this)
        binding.tvExam1.setOnClickListener(this)
        binding.tvExam2.setOnClickListener(this)
        binding.tvExam3.setOnClickListener(this)
        binding.tvExam4.setOnClickListener(this)
        binding.tvExam5.setOnClickListener(this)
    }

    /// xu li click va ban data qua fragment va replcae
    override fun clickViews(view: View) {
        super.clickViews(view)
        val listId = listOf(
            R.id.ic_random,
            R.id.tv_exam1,
            R.id.tv_exam2,
            R.id.tv_exam3,
            R.id.tv_exam4,
            R.id.tv_exam5
        )

    }

    private fun replaceFragment(fragment: Fragment, idView: Int) {
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}