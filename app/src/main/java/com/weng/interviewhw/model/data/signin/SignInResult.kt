package com.weng.interviewhw.model.data.signin

import com.weng.interviewhw.model.data.share.Status

/**
 * 呼api註冊並回傳的結果, 此為repository傳輸用的Data
 */
data class SignInResult(
    val status: Status,
    val token: String,
    val error: String
)
