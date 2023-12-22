package com.example.learndriver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndriver.databinding.ItemTopicBinding
import com.example.learndriver.iClickItemInterface.iClickItemListener
import com.example.learndriver.model.Topic

class TopicAdapter(private val topicList: List<Topic>, private val listener: iClickItemListener) :
    RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int = topicList.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topicList[position]
        holder.binding.tvTopic.text = topic.topic
        holder.binding.tvTotal.text = topic.totalNumber
        holder.binding.tvCurrentNumber.text = topic.currentNumber
        holder.binding.imgTopic.setImageResource(topic.imgSrc)
        holder.binding.prgBarTopic.progress = topic.rateProgressbar
        holder.binding.layoutAllQuestion.setOnClickListener { listener.onTopicClicked(topic) }
    }

    inner class TopicViewHolder(val binding: ItemTopicBinding) :
        RecyclerView.ViewHolder(binding.root)
}