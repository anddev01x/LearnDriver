package com.example.learndriver.data_local.share_preferences

import android.annotation.SuppressLint
import android.content.Context

object DataLocalManager {
    private const val PREF_BOOLEAN = "PREF_BOOLEAN"

    @SuppressLint("StaticFieldLeak")
    private lateinit var mySharePreferences: MySharePreferences

    fun init(context: Context) {
        mySharePreferences = MySharePreferences(context)
    }

    fun setBoolean(isCheck: Boolean) {
        mySharePreferences.setFirstTime(PREF_BOOLEAN, isCheck)
    }

    fun getBoolean(): Boolean {
        return mySharePreferences.getFirstTime(PREF_BOOLEAN)
    }
}