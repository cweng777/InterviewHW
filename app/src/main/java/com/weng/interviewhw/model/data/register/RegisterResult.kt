package com.weng.interviewhw.model.data.register

import com.weng.interviewhw.model.data.share.Status

//for repository use
data class RegisterResult(
    val status: Status,
    val token: String,
    val error: String
)
