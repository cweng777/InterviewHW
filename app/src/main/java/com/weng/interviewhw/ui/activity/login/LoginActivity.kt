package com.weng.interviewhw.ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.ActivityLoginBinding
import com.weng.interviewhw.ui.fragment.login.SignInFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frameLayout, SignInFragment.newInstance())
            .commit()
    }

    fun replaceFragment(targetFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frameLayout, targetFragment)
            .addToBackStack(targetFragment::class.java.simpleName)
            .commit()
    }
}