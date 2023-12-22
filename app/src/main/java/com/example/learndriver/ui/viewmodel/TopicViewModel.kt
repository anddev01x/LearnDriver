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
            Topic(R.drawable.ic_fire, "CÂU ĐIỂM LIỆT", "60 câu" , 25, "15/60"),
            Topic(R.drawable.ic_rules, "KHÁI NIỆM VÀ QUY TẮC", "192 câu", 50, "110/192"),
            Topic(R.drawable.ic_brain, "VĂN HÓA VÀ ĐẠO ĐỨC", "21 câu", 30, "9/21"),
            Topic(R.drawable.ic_steering_wheel, "KỸ THUẬT LÁI XE", "91 câu", 12, "5/91"),
            Topic(R.drawable.ic_notica_broad, "BIỂN BÁO ĐƯỜNG BỘ", "182 câu", 9, "10/182"),
            Topic(R.drawable.ic_uphill, "SA HÌNH", "114 câu", 70, "100/114")
        )
        mListTopicLiveData.value = listTopic
    }
}