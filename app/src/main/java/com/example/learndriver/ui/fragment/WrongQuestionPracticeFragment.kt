package com.example.learndriver.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.learndriver.databinding.FragmentWrongQuestionPracticeBinding
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel

class WrongQuestionPracticeFragment : BaseFragment<FragmentWrongQuestionPracticeBinding>() {
    private val viewModel: AllQuestionViewModel by activityViewModels()

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongQuestionPracticeBinding {
        return FragmentWrongQuestionPracticeBinding.inflate(layoutInflater, container, false)
    }

    override fun initViews() {
        setUpViewPage()
    }

    private fun setUpViewPage() {

    }
}
