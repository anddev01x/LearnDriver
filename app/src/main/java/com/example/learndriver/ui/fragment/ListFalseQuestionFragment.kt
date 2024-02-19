package com.example.learndriver.ui.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.learndriver.adapter.QuestionResultAdapter
import com.example.learndriver.databinding.FragmentListFalseQuestionBinding
import com.example.learndriver.iClickItemInterface.iClickItemQuestionListener
import com.example.learndriver.model.Question
import com.example.learndriver.ui.activity.DetailExamActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ListFalseQuestionFragment : BaseFragment<FragmentListFalseQuestionBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListFalseQuestionBinding {
        return FragmentListFalseQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val listQuestion = viewModel.getListFalseQuestionExam()
        val adapter = QuestionResultAdapter(listQuestion, object : iClickItemQuestionListener {
            override fun onResultQuestionClicked(question: Question) {
                val intent = Intent(requireContext(), DetailExamActivity::class.java)
                intent.putExtra("question", question)
                startActivity(intent)
            }
        })
        binding.recyclerView.adapter = adapter
    }
}