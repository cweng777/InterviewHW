package com.weng.interviewhw.extension

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

/**
 * 確認Email格式是否符合
 */
fun checkEmailFormat(emailInput: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
}

/**
 * 確認password格式是否符合
 */
fun checkPasswordFormat(passwordInput: String): Boolean {
    val pattern = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-zA-Z])" +      //at least 1 uppercase or lowercase letter
            "(?=\\S+$)" +           //no white spaces
            ".{6,}" +               //at least 6 characters
            "$")
    val matcher = pattern.matcher(passwordInput)
    return matcher.matches()
}

/**
 * 驗證Email
 */
fun validateEmail(emailInput: String, textInputLayout: TextInputLayout): Boolean {
    return when {
        emailInput.isEmpty() -> {
            textInputLayout.error = "Email can not be empty"
            false
        }
        !checkEmailFormat(emailInput) -> {
            textInputLayout.error = "Email's format is wrong"
            false
        }
        else -> {
            textInputLayout.error = null
            true
        }
    }
}

/**
 * 驗證Password
 */
 fun validatePassword(passwordInput: String, textInputLayout: TextInputLayout): Boolean {
    return when {
        passwordInput.isEmpty() -> {
            textInputLayout.error = "Password can not be empty"
            false
        }
        !checkPasswordFormat(passwordInput) -> {
            textInputLayout.error =
                "Password's format is wrong:\n" +
                        "  please input 6 letters and digits\n" +
                        "  at least 1 lowercase or uppercase letter\n" +
                        "  at least 1 digit\n" +
                        "  no empty space letter allowed"
            false
        }
        else -> {
            textInputLayout.error = null
            true
        }
    }
}

/**
 * 驗證firstName 或 lastName
 */
fun validateName(nameInput: String, textInputLayout: TextInputLayout): Boolean {
    return when {
        nameInput.isEmpty() -> {
            textInputLayout.error = "name can not be empty"
            false
        }
        else -> {
            textInputLayout.error = null
            true
        }
    }
}


