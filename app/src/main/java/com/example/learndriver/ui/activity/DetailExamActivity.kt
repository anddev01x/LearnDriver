package com.example.learndriver.ui.activity

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import com.bumptech.glide.Glide
import com.example.learndriver.R
import com.example.learndriver.databinding.ActivityDetailExamBinding
import com.example.learndriver.model.Question

class DetailExamActivity : BaseAct<ActivityDetailExamBinding>() {
    private lateinit var question: Question
    private var answeredCorrectly = false
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

    override fun initViewBinding(): ActivityDetailExamBinding {
        return ActivityDetailExamBinding.inflate(layoutInflater)
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
        binding.icFinish.setOnClickListener(this)
        getDataFromBundle()
    }

    private fun getDataFromBundle() {
        question = (intent.getSerializableExtra("question") as? Question)!!
        showQuestion(question)
    }

    @SuppressLint("SetTextI18n")
    private fun showQuestion(question: Question) {
        binding.tvQuestion.text = question.question
        if (question.positionIndexRandom != null) {
            binding.tvCurrentQuestion.text = question.positionIndexRandom.toString()
        } else {
            binding.tvCurrentQuestion.text = getLastTwoOrThreeDigits(question.id)
        }
        binding.tvAnswer1.text = question.option.a
        binding.tvAnswer2.text = question.option.b
        binding.tvAnswer3.text = question.option.c
        binding.tvAnswer4.text = question.option.d
        if (question.image.image1 != null) {
            binding.imgQuestion.visibility = View.VISIBLE
            Glide.with(this)
                .load(question.image.image1)
                .error(R.drawable.img_error)
                .into(binding.imgQuestion)
        }
        if (question.option.c == null) {
            binding.layoutAnswer3.visibility = View.GONE
        }
        if (question.option.d == null) {
            binding.layoutAnswer4.visibility = View.GONE
        }
        binding.tvComment.text = question.suggest
        var foundCorrectAnswer = false
        for (i in answerText.indices) {
            if (question.currentAnswer == question.answer) {
                setupResultViews()
                handleCorrectAnswer(i)
                foundCorrectAnswer = true
                break
            }
        }

        if (!foundCorrectAnswer && question.currentAnswer != null) {
            setupResultViews()
            for (i in answerText.indices) {
                handleIncorrectAnswer(i)
                break
            }
        }
        if (question.currentAnswer == null) {
            handleNullAnswer()
        }

    }

    private fun handleNullAnswer() {
        for ((index, answer) in layoutAnswer.withIndex()) {
            answer.setOnClickListener {
                onChooseAnswer(index)
            }
        }
    }

    private fun onChooseAnswer(index: Int) {
        if (answeredCorrectly) {
            return
        }
        setupResultViews()
        binding.tvComment.text = question.suggest

        if (isAnswerCorrect(index)) {
            handleCorrectAnswer(index)
        } else {
            handleIncorrectAnswer(index)
        }
    }

    private fun isAnswerCorrect(index: Int): Boolean {
        return answerText[index] == question.answer
    }

    private fun setupResultViews() {
        binding.layoutResult.visibility = View.VISIBLE
        binding.layoutComment.visibility = View.VISIBLE
        binding.tvComment.visibility = View.VISIBLE
        binding.imgCheck.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun handleCorrectAnswer(index: Int) {
        val trueImage = trueImageResource[index]
        answerImage[index].setImageResource(trueImage)

        binding.apply {
            tvResult.text = resources.getString(R.string.tv_true)
            tvTrueAnswer.text = "Bạn đã chọn đúng"
            imgCheck.setImageResource(R.drawable.ic_true)
            icResult.setImageResource(R.drawable.ic_big_true)
        }
        answeredCorrectly = true
    }

    @SuppressLint("SetTextI18n")
    private fun handleIncorrectAnswer(index: Int) {
        val wrongImage = wrongImageResource[index]
        answerImage[index].setImageResource(wrongImage)

        //Loop answerText -> get index -> check database ->set image
        for (i in answerImage.indices) {
            if (answerText[i] == question.answer) {
                val trueImage = trueImageResource[i]
                answerImage[i].setImageResource(trueImage)
                binding.tvTrueAnswer.text = "Đáp án đúng: Số ${i + 1}"
            }
        }

        binding.apply {
            tvResult.text = "SAI"
            imgCheck.setImageResource(R.drawable.ic_false)
            icResult.setImageResource(R.drawable.ic_big_wrong)
            tvTrueAnswer.visibility = View.VISIBLE
        }
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (R.id.ic_finish == view.id) return super.onBackPressed()
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
