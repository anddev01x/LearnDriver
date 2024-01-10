package com.example.learndriver.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.activity.viewModels
import com.example.learndriver.R
import com.example.learndriver.databinding.ActivityQuestionBinding
import com.example.learndriver.ui.fragment.AllQuestionFragment
import com.example.learndriver.ui.fragment.NotStudyFragment
import com.example.learndriver.ui.fragment.WrongQuestionFragment
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class QuestionActivity : BaseAct<ActivityQuestionBinding>() {
    private val viewModel: AllQuestionViewModel by viewModels()
    private var selectTabNumber = 1
    private var isIconNew = true
    override fun initViewBinding(): ActivityQuestionBinding {
        return ActivityQuestionBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.icBack.setOnClickListener(this)
        binding.icStar.setOnClickListener(this)
        binding.icNextQuestion.setOnClickListener(this)
        binding.icBackQuestion.setOnClickListener(this)
        binding.tabLayout1.setOnClickListener { selectTab(1) }
        binding.tabLayout2.setOnClickListener { selectTab(2) }
        binding.tabLayout3.setOnClickListener { selectTab(3) }
    }

    @SuppressLint("CommitTransaction")
    private fun selectTab(tabNumber: Int) {
        val selectTextView: TextView
        val nonSelectTextView1: TextView
        val nonSelectTextView2: TextView
        //When tabNumber == 1, select tab1 and nonSelect tab 2,3
        when (tabNumber) {
            1 -> {
                selectTextView = binding.tabLayout1
                nonSelectTextView1 = binding.tabLayout2
                nonSelectTextView2 = binding.tabLayout3

                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainView, AllQuestionFragment::class.java, null)
                    .commit()
            }

            2 -> {
                selectTextView = binding.tabLayout2
                nonSelectTextView1 = binding.tabLayout1
                nonSelectTextView2 = binding.tabLayout3

                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainView, NotStudyFragment::class.java, null)
                    .commit()
            }

            else -> {
                selectTextView = binding.tabLayout3
                nonSelectTextView1 = binding.tabLayout1
                nonSelectTextView2 = binding.tabLayout2

                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainView, WrongQuestionFragment::class.java, null)
                    .commit()
            }
        }


        val slideTo = (tabNumber - selectTabNumber) * selectTextView.width

        //Creating translate Animation
        val translateAnimation = TranslateAnimation(0F, slideTo.toFloat(), 0F, 0F)
        translateAnimation.duration = 130

        when (selectTabNumber) {
            1 -> binding.tabLayout1.startAnimation(translateAnimation)
            2 -> binding.tabLayout2.startAnimation(translateAnimation)
            3 -> binding.tabLayout3.startAnimation(translateAnimation)
        }
        translateAnimation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    //do not thing
                }

                @SuppressLint("ResourceType")
                override fun onAnimationEnd(animation: Animation?) {
                    //Change design of select TabLayout
                    selectTextView.setBackgroundResource(R.drawable.custom_click_backgound_tablayout)
                    selectTextView.setTypeface(null, Typeface.BOLD)
                    selectTextView.setTextColor(Color.BLACK)

                    //Change design of non select TabLayout
                    nonSelectTextView1.setBackgroundResource(resources.getColor(android.R.color.transparent))
                    nonSelectTextView1.setTypeface(null, Typeface.NORMAL)
                    nonSelectTextView1.setTextColor(Color.parseColor("#BFFFFFFF"))

                    nonSelectTextView2.setBackgroundResource(resources.getColor(android.R.color.transparent))
                    nonSelectTextView2.setTypeface(null, Typeface.NORMAL)
                    nonSelectTextView2.setTextColor(Color.parseColor("#BFFFFFFF"))
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    //do not thing
                }
            })
        selectTabNumber = tabNumber
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_back == view.id) return super.onBackPressed()
        if (R.id.ic_star == view.id) {
            if (isIconNew) {
                binding.icStar.setImageResource(R.drawable.ic_star_change_color)
            } else {
                binding.icStar.setImageResource(R.drawable.ic_star)
            }
            isIconNew = !isIconNew
        }
        if (R.id.ic_next_question == view.id) {
            onNextButtonClick()
        }
        if (R.id.ic_back_question == view.id) {
            onBackButtonClick()
        }
    }

    @SuppressLint("SetTextI18n")
    fun onNextButtonClick() {
        if (selectTabNumber == 1) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainView)
            if (fragment is AllQuestionFragment) {
                binding.icBackQuestion.visibility = View.VISIBLE
                binding.viewSpace.visibility = View.GONE
                fragment.goToNextPage()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBackButtonClick() {
        if (selectTabNumber == 1) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainView)
            if (fragment is AllQuestionFragment) {
                binding.icBackQuestion.visibility = View.VISIBLE
                fragment.backToPage()
                if (binding.tvCurrentQuestion.text.toString().toInt() < 2) {
                    binding.icBackQuestion.visibility = View.GONE
                    binding.viewSpace.visibility = View.VISIBLE
                }
            }
        }
    }

}
