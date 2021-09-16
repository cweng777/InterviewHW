package com.weng.interviewhw.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentSignInBinding
import com.weng.interviewhw.extension.*
import com.weng.interviewhw.model.data.signin.ui.SignInResultUI
import com.weng.interviewhw.ui.activity.login.LoginActivity
import com.weng.interviewhw.ui.activity.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SignInViewModel>()

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)
        setEventListener()
        observeViewMode()
    }

    private fun observeViewMode() {
        lifecycleScope.launchWhenStarted {
            viewModel.signInState.observe(viewLifecycleOwner) {
                when (it) {
                    SignInResultUI.Loading -> {
                        binding.progressbarConstraintLayout.isVisible = true
                    }
                    SignInResultUI.Success -> {
                        binding.progressbarConstraintLayout.isVisible = false
                        Toast.makeText(requireContext(), "sign in success!!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        activity?.finish()
                    }
                    is SignInResultUI.Failure -> {
                        binding.progressbarConstraintLayout.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
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
        //登入
        viewModel.signIn(emailInput, passwordInput)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}