package com.weng.interviewhw.model.remotedatasource.body.signin

data class SignInResponseBody(
    val status: Int,
    val token: String?,
    val error: String?
)
