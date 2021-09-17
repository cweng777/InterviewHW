package com.weng.interviewhw.model.data.register.ui

/**
 * 呼api註冊並回傳的結果, 此為UI顯示用的Data
 */
sealed class RegisterResultUI {
    object Loading: RegisterResultUI()
    object Success: RegisterResultUI()
    data class Failure(val message: String): RegisterResultUI()
}
