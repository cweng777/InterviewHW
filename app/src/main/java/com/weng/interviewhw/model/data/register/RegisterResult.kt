package com.weng.interviewhw.model.data.register

import com.weng.interviewhw.model.data.share.Status

/**
 * 呼api註冊並回傳的結果, 此為repository傳輸用的Data
 */
data class RegisterResult(
    val status: Status,
    val token: String,
    val error: String
)
