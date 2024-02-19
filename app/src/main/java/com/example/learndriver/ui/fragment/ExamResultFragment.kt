package com.example.learndriver.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentExamResultBinding
import com.example.learndriver.ui.activity.ExamTestActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel


class ExamResultFragment : BaseFragment<FragmentExamResultBinding>() {
    private var selectTabNumber = 1
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExamResultBinding {
        return FragmentExamResultBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        setUpResultExam()
        binding.tabLayout1.setOnClickListener { selectTab(1) }
        binding.tabLayout2.setOnClickListener { selectTab(2) }
        binding.tabLayout3.setOnClickListener { selectTab(3) }
        binding.tabLayout4.setOnClickListener { selectTab(4) }
        binding.icBack.setOnClickListener(this)
        binding.btRestart.setOnClickListener(this)
    }

    private fun setUpResultExam() {
        val getListTrueQuestion = viewModel.getListTrueQuestionExam()
        val getListFalseQuestion = viewModel.getListFalseQuestionExam()
        val getListWarningQuestion = viewModel.getListWarningQuestionExam()

        binding.tvTotalTrue.text = getListTrueQuestion.size.toString()
        binding.tvTotalFalse.text = getListFalseQuestion.size.toString()
        binding.tvTotalWarning.text = getListWarningQuestion.size.toString()

        binding.btRestart.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.green)
        binding.tvCorrectAnswer.text = getListTrueQuestion.size.toString()
        if (getListTrueQuestion.size < 32) {
            binding.tvResult.text = "RỚT"
        } else {
            binding.tvResult.text = "ĐẬU"
        }

        viewModel.countdownTime.observe(viewLifecycleOwner) {
            val remainingTimeInMillis = (22 * 60 * 1000) - it
            val remainingMinutes = remainingTimeInMillis / (60 * 1000)
            val remainingSeconds = (remainingTimeInMillis % (60 * 1000)) / 1000

            binding.tvTime.text =
                String.format("%02d:%02d", remainingMinutes, remainingSeconds)

        }
    }

    private fun selectTab(tabNumber: Int) {
        val selectTabLayout: LinearLayout
        val nonSelectTabLayout1: LinearLayout
        val nonSelectTabLayout2: LinearLayout
        val nonSelectTabLayout3: LinearLayout

        //When tabNumber == 1, select tab1 and nonSelect tab 2,3
        when (tabNumber) {
            1 -> {
                selectTabLayout = binding.tabLayout1
                nonSelectTabLayout1 = binding.tabLayout2
                nonSelectTabLayout2 = binding.tabLayout3
                nonSelectTabLayout3 = binding.tabLayout4

                childFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ListTotalQuestionFragment())
                    .commit()
            }

            2 -> {
                selectTabLayout = binding.tabLayout2
                nonSelectTabLayout1 = binding.tabLayout1
                nonSelectTabLayout2 = binding.tabLayout3
                nonSelectTabLayout3 = binding.tabLayout4

                childFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ListTrueQuestionFragment())
                    .commit()
            }

            3 -> {
                selectTabLayout = binding.tabLayout3
                nonSelectTabLayout1 = binding.tabLayout1
                nonSelectTabLayout2 = binding.tabLayout2
                nonSelectTabLayout3 = binding.tabLayout4

                childFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ListFalseQuestionFragment())
                    .commit()
            }

            else -> {
                selectTabLayout = binding.tabLayout4
                nonSelectTabLayout1 = binding.tabLayout1
                nonSelectTabLayout2 = binding.tabLayout2
                nonSelectTabLayout3 = binding.tabLayout3

                childFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ListWarningQuestionFragment())
                    .commit()
            }
        }

        val slideTo = (tabNumber - selectTabNumber) * selectTabLayout.width

        //Creating translate Animation
        val translateAnimation = TranslateAnimation(0F, slideTo.toFloat(), 0F, 0F)
        translateAnimation.duration = 130

        when (selectTabNumber) {
            1 -> binding.tabLayout1.startAnimation(translateAnimation)
            2 -> binding.tabLayout2.startAnimation(translateAnimation)
            3 -> binding.tabLayout3.startAnimation(translateAnimation)
            4 -> binding.tabLayout4.startAnimation(translateAnimation)
        }

        translateAnimation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    // Do nothing
                }

                override fun onAnimationEnd(animation: Animation?) {

                    // Change design of non-select TabLayouts
                    nonSelectTabLayout1.setBackgroundResource(R.color.gray_500)
                    binding.tvList.setTextColor(Color.BLACK)
                    binding.icList.setImageResource(R.drawable.ic_list)

                    nonSelectTabLayout2.setBackgroundResource(R.color.gray_500)
                    binding.tvTotalTrue.setTextColor(Color.BLACK)
                    binding.icTotalTrue.setImageResource(R.drawable.ic_total_true)

                    nonSelectTabLayout3.setBackgroundResource(R.color.gray_500)
                    binding.tvTotalFalse.setTextColor(Color.BLACK)
                    binding.icTotalFalse.setImageResource(R.drawable.ic_total_false)

                    binding.tvTotalWarning.setTextColor(Color.BLACK)
                    binding.icTotalWarning.setImageResource(R.drawable.ic_total_warning)

                    // Change design of select TabLayout
                    when (selectTabNumber) {
                        1 -> {
                            selectTabLayout.setBackgroundResource(R.color.gray_700)
                            binding.tvList.setTextColor(Color.WHITE)
                            binding.icList.setImageResource(R.drawable.ic_white_list)
                        }

                        2 -> {
                            selectTabLayout.setBackgroundResource(R.color.purple_800)
                            binding.tvTotalTrue.setTextColor(Color.WHITE)
                            binding.icTotalTrue.setImageResource(R.drawable.ic_total_white_true)
                        }

                        3 -> {
                            selectTabLayout.setBackgroundResource(R.color.red)
                            binding.tvTotalFalse.setTextColor(Color.WHITE)
                            binding.icTotalFalse.setImageResource(R.drawable.ic_total_white_false)
                        }

                        4 -> {
                            selectTabLayout.setBackgroundResource(R.color.yellow)
                            binding.tvTotalWarning.setTextColor(Color.WHITE)
                            binding.icTotalWarning.setImageResource(R.drawable.ic_total_white_warning)
                        }
                    }
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    // Do nothing
                }
            })

        selectTabNumber = tabNumber
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_back == view.id) return startActivity(
            Intent(
                requireContext(),
                ExamTestActivity::class.java
            )
        )
        if (R.id.bt_restart == view.id) return requireActivity().supportFragmentManager.popBackStack()
    }
}