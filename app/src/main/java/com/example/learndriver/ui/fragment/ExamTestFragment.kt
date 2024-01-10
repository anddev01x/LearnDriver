package com.example.learndriver.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentExamTestBinding
import com.example.learndriver.ui.activity.MainActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ExamTestFragment : BaseFragment<FragmentExamTestBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()
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
        binding.icBack.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        binding.icTip.setOnClickListener { onShowRulesDialog() }
    }

    private fun onShowRulesDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_rules_exam)
        val window: Window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes = window.attributes
        windowAttributes.gravity = Gravity.CENTER

        val btCancel: Button = dialog.findViewById(R.id.bt_cancel)
        btCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

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
        val selectedId = view.id
        if (listId.contains(selectedId)) {
            //random getRandom/per click
            viewModel.getRandomQuestion()
            //number 1 - 5 check isFirstTime
            //get Data only first time
            onShowRulesStartDialog(selectedId)
        }
    }

    private fun onShowRulesStartDialog(selectedId: Int) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_start_exam)
        val window: Window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes = window.attributes
        windowAttributes.gravity = Gravity.CENTER

        val btStart: Button = dialog.findViewById(R.id.bt_start)
        val btCancel: Button = dialog.findViewById(R.id.bt_cancel)

        btStart.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            R.color.brown_200
        )
        btStart.setOnClickListener {
            replaceFragment(ExamTestTwoFragment(), selectedId)
            dialog.dismiss()
        }
        btCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun replaceFragment(fragment: Fragment, selectedId: Int) {
        val bundle = Bundle()
        bundle.putInt("selectedId", selectedId)
        fragment.arguments = bundle
        val fragmentManager = parentFragmentManager
        fragmentManager.commit {
            replace(R.id.fragmentContainView, fragment)
        }
        Log.i("ExamTestFragment", "replaceFragment: $selectedId")
    }
}