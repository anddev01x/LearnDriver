package com.example.learndriver.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndriver.R
import com.example.learndriver.databinding.ItemResultQuestionBinding
import com.example.learndriver.iClickItemInterface.iClickItemQuestionListener
import com.example.learndriver.model.Question

class QuestionResultAdapter(
    private var listQuestion: List<Question>,
    private val listener: iClickItemQuestionListener
) :
    RecyclerView.Adapter<QuestionResultAdapter.QuestionResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionResultViewHolder {
        val binding =
            ItemResultQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionResultViewHolder(binding)
    }

    override fun getItemCount(): Int = listQuestion.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionResultViewHolder, position: Int) {
        val question = listQuestion[position]
        if (listQuestion.size == 35) {
            val questionNumber = position + 1
            question.positionIndexRandom = questionNumber
            holder.binding.tvQuestion.text = "Câu ${position + 1}"
        } else {
            holder.binding.tvQuestion.text = "Câu ${question.positionIndexRandom.toString()}"
        }
        if (question.answer == question.currentAnswer) {
            holder.binding.icListResult.setImageResource(R.drawable.ic_true_result)
        }
        if ((question.answer != question.currentAnswer && question.currentAnswer != null))
            holder.binding.icListResult.setImageResource(R.drawable.ic_false_result)
        if (question.currentAnswer == null) {
            holder.binding.icListResult.setImageResource(R.drawable.ic_warning_result)
        }
        holder.binding.layoutItemResultQuestion.setOnClickListener {
            listener.onResultQuestionClicked(
                question
            )
        }

    }


    inner class QuestionResultViewHolder(val binding: ItemResultQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}