package com.weng.interviewhw.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.ActivityMainBinding
import com.weng.interviewhw.ui.activity.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEventListener()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.logoutState.observe(this) {
            if (it == true) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setEventListener() {
        binding.logoutButotn.setOnClickListener {
            viewModel.logout()
        }
    }
}