package com.example.learndriver.ui.activity

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.learndriver.R
import com.example.learndriver.adapter.ViewPageAdapter
import com.example.learndriver.databinding.ActivityTopicQuestionBinding
import com.example.learndriver.model.Topic
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel
import kotlinx.coroutines.launch

class TopicQuestionActivity : BaseAct<ActivityTopicQuestionBinding>() {
    private val allQuestionViewModel: AllQuestionViewModel by viewModels()

    override fun initViewBinding(): ActivityTopicQuestionBinding {
        return ActivityTopicQuestionBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.icBack.setOnClickListener(this)
        binding.icBackQuestion.setOnClickListener(this)
        binding.icNextQuestion.setOnClickListener(this)
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayQuestion(idStart: String, idEnd: String) {
        lifecycleScope.launch {
            try {
                val result = allQuestionViewModel.getPartQuestion(idStart, idEnd)
                result.let {
                    Log.e("TAG", "displayQuestion: ")
                    val adapter = ViewPageAdapter(supportFragmentManager, lifecycle, it)
                    binding.viewPageTopicQuestion.adapter = adapter
                    binding.viewPageTopicQuestion.offscreenPageLimit = 15
                    binding.tvTotalQuestion.text = result.size.toString()
                    binding.viewPageTopicQuestion.registerOnPageChangeCallback(object :
                        ViewPager2.OnPageChangeCallback() {
                        @SuppressLint("SetTextI18n")
                        override fun onPageSelected(position: Int) {
                            binding.tvCurrentQuestion.text = (position + 1).toString()
                            Log.i("TAG", "Position page:  $position")
                            if (position == 0) {
                                binding.icBackQuestion.visibility = View.GONE
                                binding.viewSpace.visibility = View.VISIBLE
                            } else {
                                binding.icBackQuestion.visibility = View.VISIBLE
                                binding.viewSpace.visibility = View.GONE
                            }
                        }

                    })
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error: ${e.message}")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displaySpecialQuestion() {
        lifecycleScope.launch {
            try {
                val result = allQuestionViewModel.getSpecialQuestion()
                result.let {
                    val adapter = ViewPageAdapter(supportFragmentManager, lifecycle, it)
                    binding.viewPageTopicQuestion.adapter = adapter
                    binding.viewPageTopicQuestion.offscreenPageLimit = 15
                    binding.tvTotalQuestion.text = result.size.toString()
                    binding.viewPageTopicQuestion.registerOnPageChangeCallback(object :
                        ViewPager2.OnPageChangeCallback() {
                        @SuppressLint("SetTextI18n")
                        override fun onPageSelected(position: Int) {
                            binding.tvCurrentQuestion.text = (position + 1).toString()
                            Log.i("TAG", "Position page:  $position")
                            if (position == 0) {
                                binding.icBackQuestion.visibility = View.GONE
                                binding.viewSpace.visibility = View.VISIBLE
                            } else {
                                binding.icBackQuestion.visibility = View.VISIBLE
                                binding.viewSpace.visibility = View.GONE
                            }
                        }

                    })
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error: ${e.message}")
            }
        }
    }

    private fun getData() {
        val topic = intent.getSerializableExtra("topic_key") as Topic
        Log.i("initViews", "initViews: $topic ")
        when (topic.topic) {
            "CÂU ĐIỂM LIỆT" -> {
                binding.tvTopic.text = topic.topic
                displaySpecialQuestion()
            }

            "KHÁI NIỆM VÀ QUY TẮC" -> {
                binding.tvTopic.text = topic.topic
                displayQuestion("10001", "10192")
            }

            "VĂN HÓA VÀ ĐẠO ĐỨC" -> {
                binding.tvTopic.text = topic.topic
                displayQuestion("10193", "10213")
            }

            "KỸ THUẬT LÁI XE" -> {
                binding.tvTopic.text = topic.topic
                displayQuestion("10214", "10304")
            }

            "BIỂN BÁO ĐƯỜNG BỘ" -> {
                binding.tvTopic.text = topic.topic
                displayQuestion("10305", "10486")
            }

            "SA HÌNH" -> {
                binding.tvTopic.text = topic.topic
                displayQuestion("10487", "10600")
            }
        }
    }

    override fun clickViews(view: View) {
        super.clickViews(view)
        if (view.id == R.id.ic_back) return onBackPressed()
        if (view.id == R.id.ic_back_question) return binding.viewPageTopicQuestion.setCurrentItem(
            binding.viewPageTopicQuestion.currentItem - 1
        )
        if (view.id == R.id.ic_next_question) return binding.viewPageTopicQuestion.setCurrentItem(
            binding.viewPageTopicQuestion.currentItem + 1
        )
    }
}
