package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndriver.adapter.TopicAdapter
import com.example.learndriver.databinding.FragmentHomeBinding
import com.example.learndriver.iClickItemInterface.iClickItemListener
import com.example.learndriver.model.Topic
import com.example.learndriver.ui.activity.PracticeActivity
import com.example.learndriver.ui.activity.QuestionActivity
import com.example.learndriver.ui.activity.TopicQuestionActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel
import com.example.learndriver.ui.viewmodel.TopicViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var viewModel: TopicViewModel
    private val model: AllQuestionViewModel by activityViewModels()
    lateinit var adapter: TopicAdapter
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        binding.icExamTopic.setOnClickListener(this)
        binding.icIdea.setOnClickListener(this)
        binding.icWrongQuestion.setOnClickListener(this)
        binding.icFilterQuestion.setOnClickListener(this)
        viewModel = ViewModelProvider(this)[TopicViewModel::class.java]
        setUpRecyclerView()
        binding.layoutAllQuestion.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    QuestionActivity::class.java
                )
            )
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.listTopicLiveData.observe(requireActivity()) {
            if (it != null) {
                adapter = TopicAdapter(it, object : iClickItemListener {
                    override fun onTopicClicked(topic: Topic) {
                        val intent = Intent(requireContext(), TopicQuestionActivity::class.java)
                        intent.putExtra("topic_key", topic)
                        startActivity(intent)
                    }
                })
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)
        val selectedId = view.id
        val intent = Intent(requireContext(), PracticeActivity::class.java)
        intent.putExtra("selectedItemId", selectedId)
        startActivity(intent)

    }
}

