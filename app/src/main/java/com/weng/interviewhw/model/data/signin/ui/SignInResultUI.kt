package com.weng.interviewhw.model.data.signin.ui

sealed class SignInResultUI{
    object Loading: SignInResultUI()
    object Success: SignInResultUI()
    data class Failure(val message: String): SignInResultUI()
}
