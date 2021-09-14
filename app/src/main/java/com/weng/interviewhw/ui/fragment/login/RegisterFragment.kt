package com.weng.interviewhw.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentRegisterBinding
import com.weng.interviewhw.extension.hideKeyboard
import com.weng.interviewhw.extension.validateEmail
import com.weng.interviewhw.extension.validateName
import com.weng.interviewhw.extension.validatePassword


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        setEventListener()
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
        //todo: viewModel register

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}