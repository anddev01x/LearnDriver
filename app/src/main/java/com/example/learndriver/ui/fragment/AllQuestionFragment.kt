package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.FragmentAllQuestionBinding
import com.example.learndriver.ui.activity.QuestionActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel


class AllQuestionFragment : BaseFragment<FragmentAllQuestionBinding>() {
    private lateinit var model: AllQuestionViewModel

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllQuestionBinding {
        return FragmentAllQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        model = ViewModelProvider(this)[AllQuestionViewModel::class.java]
        setUpViewPage()
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun setUpViewPage() {
        val myActivityBinding = (activity as QuestionActivity).binding
        model.listAllQuestionLiveData.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            val adapter = it?.let { it1 -> ViewPageAdapter(this, it1) }
            binding.viewPageAllQuestion.adapter = adapter
            binding.viewPageAllQuestion.registerOnPageChangeCallback(object :
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

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })
        }
    }

    fun goToNextPage() {
        val viewPager = binding.viewPageAllQuestion
        viewPager.let {
            if (it.currentItem < (it.adapter?.itemCount ?: (0 - 1))) {
                it.currentItem += 1
            }
        }
    }

    fun backToPage() {
        val viewPager = binding.viewPageAllQuestion
        viewPager.let {
            if (it.currentItem < (it.adapter?.itemCount ?: (0 - 1))) {
                it.currentItem -= 1
            }
        }
    }
}
