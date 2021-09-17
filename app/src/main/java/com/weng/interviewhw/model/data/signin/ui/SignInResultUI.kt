package com.weng.interviewhw.model.data.signin.ui

/**
 * 呼api登入並回傳的結果, 此為UI顯示用的Data
 */
sealed class SignInResultUI{
    object Loading: SignInResultUI()
    object Success: SignInResultUI()
    data class Failure(val message: String): SignInResultUI()
}
