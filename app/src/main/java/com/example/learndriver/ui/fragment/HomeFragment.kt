package com.example.learndriver.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndriver.adapter.TopicAdapter
import com.example.learndriver.data_local.share_preferences.DataLocalManager
import com.example.learndriver.data_local.share_preferences.MySharePreferences
import com.example.learndriver.databinding.FragmentHomeBinding
import com.example.learndriver.ui.activity.QuestionActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel
import com.example.learndriver.ui.viewmodel.TopicViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var viewModel: TopicViewModel
    lateinit var allQuestionModel: AllQuestionViewModel
    lateinit var adapter: TopicAdapter
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        DataLocalManager.init(requireActivity())
        viewModel = ViewModelProvider(this)[TopicViewModel::class.java]
        Log.i("initViews", "initViews: 2222222222222")
        allQuestionModel = ViewModelProvider(this)[AllQuestionViewModel::class.java]

        Log.i("initViews", "initViews: 3333333333333")
        setUpRecyclerView()
//        getDataFromAPI()
        binding.icExamTopic.setOnClickListener(this)
        binding.icIdea.setOnClickListener(this)
        binding.icWrongQuestion.setOnClickListener(this)
        binding.icFilterQuestion.setOnClickListener(this)
        binding.layoutAllQuestion.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    QuestionActivity::class.java
                )
            )
        }
    }
//
//    @SuppressLint("SuspiciousIndentation")
//    private fun getDataFromAPI() {
//        val isFirstTime = DataLocalManager.getBoolean()
////        if (isFirstTime) {
//            allQuestionModel.loadDataFromAPI()
//            DataLocalManager.setBoolean(false)
//            Log.d("MyApp", "loadDataFromAPI() called")
////        }
//    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.listTopicLiveData.observe(requireActivity()) {
            if (it != null) {
                adapter = TopicAdapter(it)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)
    }
}