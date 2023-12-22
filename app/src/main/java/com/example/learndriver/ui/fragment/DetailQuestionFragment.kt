package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.learndriver.R
import com.example.learndriver.databinding.FragmentDetailQuestionBinding
import com.example.learndriver.model.Question
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class DetailQuestionFragment : BaseFragment<FragmentDetailQuestionBinding>() {
    private lateinit var question: Question
    private var answeredCorrectly = false
    private val viewModel: AllQuestionViewModel by activityViewModels()

    private val answerText = mutableListOf("a", "b", "c", "d")
    private val answerImage = mutableListOf<ImageView>()
    private val layoutAnswer = mutableListOf<TableRow>()

    private val trueImageResource = listOf(
        R.drawable.ic_answer1_true,
        R.drawable.ic_answer2_true,
        R.drawable.ic_answer3_true,
        R.drawable.ic_answer4_true
    )
    private val wrongImageResource = listOf(
        R.drawable.ic_answer1_wrong,
        R.drawable.ic_answer2_wrong,
        R.drawable.ic_answer3_wrong,
        R.drawable.ic_answer4_wrong
    )


    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailQuestionBinding {
        return FragmentDetailQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        answerImage.add(binding.icAnswer1)
        answerImage.add(binding.icAnswer2)
        answerImage.add(binding.icAnswer3)
        answerImage.add(binding.icAnswer4)

        layoutAnswer.add(binding.layoutAnswer1)
        layoutAnswer.add(binding.layoutAnswer2)
        layoutAnswer.add(binding.layoutAnswer3)
        layoutAnswer.add(binding.layoutAnswer4)
        displayQuestionFromBundle()

        if (viewModel.answersMap[question.id] != null && viewModel.answersMap[question.id]?.isNotEmpty() == true) {
            val index = (viewModel.answersMap[question.id]?.first() ?: 'a') - 'a'
            onChooseAnswer(index)
        }

        binding.layoutAnswer1.setOnClickListener(this)
        binding.layoutAnswer2.setOnClickListener(this)
        binding.layoutAnswer3.setOnClickListener(this)
        binding.layoutAnswer4.setOnClickListener(this)

        //loop layoutAnswer, index 0123  in layoutAnswer
        for ((index, answer) in layoutAnswer.withIndex()) {
            answer.setOnClickListener {
                onChooseAnswer(index)
            }
        }
    }


    private fun displayQuestionFromBundle() {
        val bundle = arguments
        if (bundle != null) {
            question = bundle.getSerializable("question_obj") as Question
            binding.tvQuestion.text = question.question
            binding.tvCurrentQuestion.text = getLastTwoOrThreeDigits(question.id)
            binding.tvAnswer1.text = question.option.a
            binding.tvAnswer2.text = question.option.b
            if (question.image.image1 != null) {
                binding.imgQuestion.visibility = View.VISIBLE
                Glide.with(this)
                    .load(question.image.image1)
                    .error(R.drawable.img_error)
                    .into(binding.imgQuestion)
            }
            if (question.option.c == null)
                binding.layoutAnswer3.visibility = View.GONE
            if (question.option.d == null)
                binding.layoutAnswer4.visibility = View.GONE
            else {
                binding.tvAnswer3.text = question.option.c
                binding.tvAnswer4.text = question.option.d
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun onChooseAnswer(index: Int) {
        val bundle = arguments
        if (bundle != null) {
            question = bundle.getSerializable("question_obj") as Question
            viewModel.updateAnswer(question.id, answerText[index])
            viewModel.getNotStudyQuestions()
//            viewModel.getWrongQuestions()
            //if choose answer is true, return
            if (answeredCorrectly) {
                return
            }
            binding.layoutResult.visibility = View.VISIBLE
            binding.layoutComment.visibility = View.VISIBLE
            binding.tvComment.visibility = View.VISIBLE
            binding.imgCheck.visibility = View.VISIBLE
            binding.tvComment.text = question.suggest
            if (answerText[index] == question.answer
                && viewModel.answersMap[question.id] == question.answer
                && viewModel.answersMap[question.id]?.isNotEmpty() == true
            ) {
                // answer choose is true
                val trueImage = trueImageResource[index]
                answerImage[index].setImageResource(trueImage)

                binding.tvResult.text = resources.getString(R.string.tv_true)
                binding.tvTrueAnswer.text = "Bạn đã chọn đúng"
                binding.imgCheck.setImageResource(R.drawable.ic_true)
                binding.icResult.setImageResource(R.drawable.ic_big_true)
                answeredCorrectly = true
                //glide load img url phía trên cau hoi 500
                // font text
            } else {
                // answer choose is false
                val wrongImage = wrongImageResource[index]
                answerImage[index].setImageResource(wrongImage)
                //Loop answerText -> get index ->set image
                for (i in answerImage.indices) {
                    if (answerText[i] == question.answer) {
                        val trueImage = trueImageResource[i]
                        answerImage[i].setImageResource(trueImage)
                        binding.tvTrueAnswer.text = "Đáp án đúng: Số ${i + 1}"
                    }
                }
                binding.tvResult.text = "SAI"
                binding.imgCheck.setImageResource(R.drawable.ic_false)
                binding.icResult.setImageResource(R.drawable.ic_big_wrong)
                binding.tvTrueAnswer.visibility = View.VISIBLE
            }
        }
    }

    private fun getLastTwoOrThreeDigits(id: String): String {
        if (id.length == 5) {
            val lastThreeDigits = id.substring(2)
            val lastThreeDigitsInt = lastThreeDigits.toInt()

            return if (lastThreeDigitsInt < 100) {
                lastThreeDigits.substring(1)
            } else {
                lastThreeDigits
            }
        }
        return id
    }


}