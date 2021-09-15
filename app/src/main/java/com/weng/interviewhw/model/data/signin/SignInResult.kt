package com.weng.interviewhw.model.data.signin

import com.weng.interviewhw.model.data.share.Status

data class SignInResult(
    val status: Status,
    val token: String,
    val error: String
)
