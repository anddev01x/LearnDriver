package com.example.learndriver.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndriver.R
import com.example.learndriver.adapter.QuestionAdapter
import com.example.learndriver.databinding.FragmentFilterQuestionBinding
import com.example.learndriver.model.Question
import com.example.learndriver.ui.activity.MainActivity
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel
import java.util.Locale


class FilterQuestionFragment : BaseFragment<FragmentFilterQuestionBinding>() {
    private var listQuestion: List<Question> = listOf()
    private val model: AllQuestionViewModel by activityViewModels()
    private lateinit var questionAdapter: QuestionAdapter
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterQuestionBinding {
        return FragmentFilterQuestionBinding.inflate(layoutInflater, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun initViews() {
        setUpRecyclerView()
        filterQuestion()
        setBackgroundSearchView()
        binding.icBack.setOnClickListener(this)
    }

    private fun setBackgroundSearchView() {
        val hint = ""
        val spannableString = SpannableString(hint)
        spannableString.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0, hint.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.searchView.queryHint = spannableString
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
        model.listAllQuestionLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                listQuestion = it
            }
            questionAdapter = it?.let { it1 -> QuestionAdapter(it1) }!!
            binding.recyclerView.adapter = questionAdapter
        }
    }

    private fun filterQuestion() {
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let { filterList(it) }
                return true
            }
        })
    }

    private fun filterList(text: String) {
        val filterList = listQuestion.filter { questions ->
            questions.question.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))
        }

        if (filterList.isEmpty()) {
            //don't something
        } else {
            questionAdapter.setFilterList(filterList)
        }
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (view.id == R.id.ic_back) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }

}