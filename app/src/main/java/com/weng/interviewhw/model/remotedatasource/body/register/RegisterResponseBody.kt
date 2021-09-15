package com.weng.interviewhw.model.remotedatasource.body.register

data class RegisterResponseBody(
    val status: Int,
    val token: String?,
    val error: String?
)