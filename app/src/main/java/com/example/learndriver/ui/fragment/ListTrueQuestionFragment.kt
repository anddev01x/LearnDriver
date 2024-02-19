package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.learndriver.adapter.QuestionResultAdapter
import com.example.learndriver.databinding.FragmentListTrueQuestionBinding
import com.example.learndriver.iClickItemInterface.iClickItemQuestionListener
import com.example.learndriver.model.Question
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class ListTrueQuestionFragment : BaseFragment<FragmentListTrueQuestionBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListTrueQuestionBinding {
        return FragmentListTrueQuestionBinding.inflate(layoutInflater, container, false)
    }
    override fun initViews() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val listQuestion = viewModel.getListResultExam()
        val result = listQuestion.filter { it.answer == it.currentAnswer }
        val adapter = QuestionResultAdapter(result, object : iClickItemQuestionListener {
            override fun onResultQuestionClicked(question: Question) {
                Toast.makeText(requireContext(), question.question, Toast.LENGTH_SHORT).show()
            }
        })
        binding.recyclerView.adapter = adapter
    }
}