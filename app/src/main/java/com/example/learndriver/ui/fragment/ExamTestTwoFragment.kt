package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.R
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.FragmentExamTestTwoBinding
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ExamTestTwoFragment : BaseFragment<FragmentExamTestTwoBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()
    private lateinit var countdownTimer: CountDownTimer
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExamTestTwoBinding {
        return FragmentExamTestTwoBinding.inflate(layoutInflater, container, false)
    }

    //observer show listViewPage
    // config examActivity + fragment
    override fun initViews() {
        startCountdown(22 * 60 * 1000)
        binding.icFinish.setOnClickListener(this)
        binding.tvFinish.setOnClickListener(this)
        binding.icBackQuestion.setOnClickListener(this)
        binding.icNextQuestion.setOnClickListener(this)
        setUpViewPage()
        binding.progressBar.visibility = View.VISIBLE
        binding.tvFinish.setOnClickListener(this)
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            //do something with onBackPress
//        }
    }

    private fun setUpViewPage() {
        viewModel.listRandomQuestionLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            val adapter = it?.let { ViewPageAdapter(parentFragmentManager, lifecycle, it) }
            binding.viewPageExam.adapter = adapter
            binding.viewPageExam.offscreenPageLimit = 35
            binding.viewPageExam.registerOnPageChangeCallback(object :
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
        if (R.id.ic_back_question == view.id) --binding.viewPageExam.currentItem

        if (R.id.ic_next_question == view.id) ++binding.viewPageExam.currentItem

        if (view.id == R.id.ic_finish || view.id == R.id.tv_finish) {
            showDialogFinishExam()
        }
    }

    private fun showDialogFinishExam() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_finish_exam)
        val window: Window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttributes = window.attributes
        windowAttributes.gravity = Gravity.CENTER

        val btFinish: Button = dialog.findViewById(R.id.bt_finish)
        val btCancel: Button = dialog.findViewById(R.id.bt_cancel)

        btFinish.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            R.color.brown_200
        )
        btFinish.setOnClickListener {
            replaceResultFragment()
            dialog.dismiss()
        }
        btCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun replaceResultFragment() {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainView, ExamResultFragment())
            commit()
        }
    }

    private fun startCountdown(totalTimeInMillis: Long) {
        val intervalInMillis = 1000

        countdownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis.toLong()) {

            override fun onTick(millisUntilFinished: Long) {
                viewModel.setCountdownTime(millisUntilFinished)
                val minutesRemaining = millisUntilFinished / (60 * 1000)
                val secondsRemaining = (millisUntilFinished % (60 * 1000)) / 1000

                val formattedTime = String.format("%02d:%02d", minutesRemaining, secondsRemaining)
                binding.tvTime.text = formattedTime
            }

            override fun onFinish() {
                replaceResultFragment()
            }
        }
        countdownTimer.start()
    }

    override fun onDestroyView() {
        countdownTimer.cancel()
        super.onDestroyView()
    }
}
