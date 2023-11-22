package com.example.learndriver.data_local.share_preferences

import android.content.Context

class MySharePreferences(private val context: Context) {
    private val MY_SHARE_PREFERENCES = "MY_SHARE_PREFERENCES"

    fun setFirstTime(key: String, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getFirstTime(key: String): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, true)
    }
}