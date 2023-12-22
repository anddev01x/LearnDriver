package com.example.learndriver.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learndriver.model.Question
import com.example.learndriver.ui.fragment.DetailQuestionFragment

class ViewPageAdapter(
    fragment: FragmentManager,
    lifecycle: Lifecycle,
    private val mListAllQuestion: List<Question> = listOf(),
) :
    FragmentStateAdapter(fragment, lifecycle) {

    override fun getItemCount(): Int {
        return mListAllQuestion.size
    }

    override fun createFragment(position: Int): Fragment {
        val question = mListAllQuestion[position]
        val detailFragment = DetailQuestionFragment()

        val bundle = Bundle()
        bundle.putSerializable("question_obj", question)
        detailFragment.arguments = bundle // Đặt đối số cho Fragment khi cần thiết

        return detailFragment
    }
}