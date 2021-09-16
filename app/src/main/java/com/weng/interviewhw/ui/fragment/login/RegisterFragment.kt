package com.weng.interviewhw.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentRegisterBinding
import com.weng.interviewhw.extension.hideKeyboard
import com.weng.interviewhw.extension.validateEmail
import com.weng.interviewhw.extension.validateName
import com.weng.interviewhw.extension.validatePassword
import com.weng.interviewhw.model.data.register.ui.RegisterResultUI
import com.weng.interviewhw.ui.activity.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<RegisterViewModel>()

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)
        setEventListener()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.registerState.observe(viewLifecycleOwner) {
                when (it) {
                    RegisterResultUI.Loading -> {
                        binding.progressbarConstraintLayout.isVisible = true
                    }
                    RegisterResultUI.Success -> {
                        binding.progressbarConstraintLayout.isVisible = false
                        Toast.makeText(requireContext(), "register success!!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        activity?.finish()
                    }
                    is RegisterResultUI.Failure -> {
                        binding.progressbarConstraintLayout.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setEventListener() {
        binding.registerBaseConstraintLayout.setOnClickListener {
            activity?.hideKeyboard()
        }
        binding.backImageView.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.registerButton.setOnClickListener {
            activity?.hideKeyboard()
            register()
        }
    }

    private fun register() {
        val firstNameInput = binding.firstNameTextInputLayout.editText?.text.toString().trim()
        val lastNameInput = binding.lastNameTextInputLayout.editText?.text.toString().trim()
        val emailInput = binding.emailTextInputLayout.editText?.text.toString().trim()
        val passwordInput = binding.passwordTextInputLayout.editText?.text.toString().trim()

        //驗證 firstName, lastName, email 和 password
        if (!validateName(
                firstNameInput,
                binding.firstNameTextInputLayout
            )
            || !validateName(
                lastNameInput,
                binding.lastNameTextInputLayout
            )
            || !validateEmail(
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
        //註冊
        viewModel.register(firstNameInput, lastNameInput, emailInput, passwordInput)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}