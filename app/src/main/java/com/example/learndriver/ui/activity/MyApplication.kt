package com.example.learndriver.ui.activity

import android.app.Application
import com.example.learndriver.data_local.share_preferences.DataLocalManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataLocalManager.init(applicationContext)
    }
}