package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.learndriver.adapter.QuestionResultAdapter
import com.example.learndriver.databinding.FragmentListWarningQuestionBinding
import com.example.learndriver.iClickItemInterface.iClickItemQuestionListener
import com.example.learndriver.model.Question
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ListWarningQuestionFragment : BaseFragment<FragmentListWarningQuestionBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListWarningQuestionBinding {
        return FragmentListWarningQuestionBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val listQuestion = viewModel.getListWarningQuestionExam()
        val adapter = QuestionResultAdapter(listQuestion, object : iClickItemQuestionListener {
            override fun onResultQuestionClicked(question: Question) {
                Toast.makeText(requireContext(), question.question, Toast.LENGTH_SHORT).show()
            }
        })
        binding.recyclerView.adapter = adapter
    }
}