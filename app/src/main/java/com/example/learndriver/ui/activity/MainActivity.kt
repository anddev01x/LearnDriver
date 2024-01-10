package com.example.learndriver.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.learndriver.R
import com.example.learndriver.data_local.share_preferences.DataLocalManager
import com.example.learndriver.ui.viewmodel.AllQuestionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: AllQuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        getDataFromApi()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainView) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigation, navController)
    }

    private fun getDataFromApi() {
        Log.i("TAG", "getDataFromApi: 01")
        if (DataLocalManager.getBoolean()) {
            viewModel.loadDataFromAPI()
            Log.i("TAG", "getDataFromApi: 02")
            DataLocalManager.setBoolean(false)
        }
    }
}