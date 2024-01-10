package com.example.learndriver.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndriver.databinding.ItemQuestionBinding
import com.example.learndriver.iClickItemInterface.iClickItemFilterQuestionListener
import com.example.learndriver.model.Question

class QuestionAdapter(
    private var listQuestion: List<Question>,
    private val listener: iClickItemFilterQuestionListener
) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterList(filterList: List<Question>) {
        this.listQuestion = filterList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listQuestion.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = listQuestion[position]
        holder.binding.tvQuestion.text = question.question
        holder.binding.tvQuestion.setOnClickListener {
            listener.onResultQuestionClicked(
                question
            )
        }
    }

    inner class QuestionViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}