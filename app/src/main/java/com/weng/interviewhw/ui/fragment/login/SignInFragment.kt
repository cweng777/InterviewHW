package com.weng.interviewhw.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentSignInBinding
import com.weng.interviewhw.extension.*
import com.weng.interviewhw.ui.activity.login.LoginActivity


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)

        setEventListener()
    }

    private fun setEventListener() {
        binding.signInBaseConstraintLayout.setOnClickListener {
            activity?.hideKeyboard()
        }
        binding.signInButton.setOnClickListener {
            activity?.hideKeyboard()
            signIn()
        }
        binding.registerTextView.setOnClickListener {
            (activity as? LoginActivity)?.replaceFragment(RegisterFragment.newInstance())
        }
    }

    private fun signIn() {
        val emailInput = binding.emailTextInputLayout.editText?.text.toString().trim()
        val passwordInput = binding.passwordTextInputLayout.editText?.text.toString().trim()
        //驗證 email 和 password
        if (!validateEmail(
                emailInput,
                binding.emailTextInputLayout
            )
            || !validatePassword(
                passwordInput,
                binding.passwordTextInputLayout
            )
        ) {
            return
        }
        //todo: viewModel sign in
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}