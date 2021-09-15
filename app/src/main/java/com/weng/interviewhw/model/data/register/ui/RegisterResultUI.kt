package com.weng.interviewhw.model.data.register.ui

sealed class RegisterResultUI {
    object Loading: RegisterResultUI()
    object Success: RegisterResultUI()
    data class Failure(val message: String): RegisterResultUI()
}
