package com.example.learndriver.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learndriver.R
import com.example.learndriver.model.Topic

class TopicViewModel : ViewModel() {
    private val mListTopicLiveData: MutableLiveData<List<Topic>?> = MutableLiveData()
    private lateinit var listTopic: List<Topic>

    val listTopicLiveData: MutableLiveData<List<Topic>?>
        get() = mListTopicLiveData

    init {
        initViews()
    }

    private fun initViews() {
        listTopic = listOf(
            Topic(R.drawable.ic_fire, "CÂU ĐIỂM LIỆT", "50 câu" , 25, "15/50"),
            Topic(R.drawable.ic_rules, "KHÁI NIỆM VÀ QUY TẮC", "250 câu", 50, "110/250"),
            Topic(R.drawable.ic_brain, "VĂN HÓA VÀ ĐẠO ĐỨC", "30 câu", 30, "9/30"),
            Topic(R.drawable.ic_steering_wheel, "KỸ THUẬT LÁI XE", "20 câu", 12, "5/20"),
            Topic(R.drawable.ic_notica_broad, "BIỂN BÁO ĐƯỜNG BỘ", "194 câu", 9, "10/194"),
            Topic(R.drawable.ic_uphill, "SA HÌNH", "150 câu", 70, "100/150")
        )
        mListTopicLiveData.value = listTopic
    }
}