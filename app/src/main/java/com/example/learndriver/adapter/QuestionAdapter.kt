package com.example.learndriver.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndriver.databinding.ItemQuestionBinding
import com.example.learndriver.model.Question

class QuestionAdapter(private var listQuestion: List<Question>) :
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
    }

    inner class QuestionViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}