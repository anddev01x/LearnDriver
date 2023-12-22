package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.FragmentWrongQuestionBinding
import com.example.learndriver.ui.activity.QuestionActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel


class WrongQuestionFragment : BaseFragment<FragmentWrongQuestionBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongQuestionBinding {
        return FragmentWrongQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun initViews() {
        setUpViewPage()
    }

    private fun setUpViewPage() {
        val myActivityBinding = (activity as QuestionActivity).binding
        viewModel.listWrongQuestionLiveData.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> ViewPageAdapter(parentFragmentManager, lifecycle, it1) }
            binding.viewPageWrongQuestion.adapter = adapter
            binding.viewPageWrongQuestion.offscreenPageLimit = 15
            binding.viewPageWrongQuestion.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                @SuppressLint("SetTextI18n")
                override fun onPageSelected(position: Int) {
                    myActivityBinding.tvCurrentQuestion.text = (position + 1).toString()
                    Log.i("TAG", "Position page:  $position")
                    if (position == 0) {
                        myActivityBinding.icBackQuestion.visibility = View.GONE
                        myActivityBinding.viewSpace.visibility = View.VISIBLE
                    } else {
                        myActivityBinding.icBackQuestion.visibility = View.VISIBLE
                        myActivityBinding.viewSpace.visibility = View.GONE
                    }
                }

            })
        }
    }
}