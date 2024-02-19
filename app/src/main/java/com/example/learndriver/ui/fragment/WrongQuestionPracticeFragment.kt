package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.R
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.FragmentWrongQuestionPracticeBinding
import com.example.learndriver.ui.activity.MainActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class WrongQuestionPracticeFragment : BaseFragment<FragmentWrongQuestionPracticeBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongQuestionPracticeBinding {
        return FragmentWrongQuestionPracticeBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        binding.icBack.setOnClickListener(this)
        binding.icBackQuestion.setOnClickListener(this)
        binding.icNextQuestion.setOnClickListener(this)
        setUpViewPage()
    }

    private fun setUpViewPage() {
        viewModel.listWrongQuestionLiveData.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> ViewPageAdapter(parentFragmentManager, lifecycle, it1) }
            binding.tvTotalQuestion.text = it!!.size.toString()
            binding.viewPageWrongQuestion.adapter = adapter
            binding.viewPageWrongQuestion.offscreenPageLimit = 15
            binding.viewPageWrongQuestion.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                @SuppressLint("SetTextI18n")
                override fun onPageSelected(position: Int) {
                    binding.tvCurrentQuestion.text = (position + 1).toString()
                    Log.i("TAG", "Position page:  $position")
                    if (position == 0) {
                        binding.icBackQuestion.visibility = View.GONE
                        binding.viewSpace.visibility = View.VISIBLE
                    } else {
                        binding.icBackQuestion.visibility = View.VISIBLE
                        binding.viewSpace.visibility = View.GONE
                    }
                }

            })
        }
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_back_question == view.id) --binding.viewPageWrongQuestion.currentItem

        if (R.id.ic_next_question == view.id) ++binding.viewPageWrongQuestion.currentItem
        if (view.id == R.id.ic_back) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }
}

