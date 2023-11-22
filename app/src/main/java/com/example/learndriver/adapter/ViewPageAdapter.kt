package com.example.learndriver.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learndriver.iClickItemInterface.iClickItemListener
import com.example.learndriver.model.Question
import com.example.learndriver.ui.fragment.AllQuestionFragment
import com.example.learndriver.ui.fragment.DetailQuestionFragment

class ViewPageAdapter(
    fragment: Fragment,
    private val mListAllQuestion: List<Question> = listOf(),
) :
    FragmentStateAdapter(fragment) {

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