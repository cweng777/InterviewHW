package com.weng.interviewhw.model.remotedatasource.body.register

/**
 * 註冊結果資料, retrofit 呼 api 完 放資料於此
 */
data class RegisterResponseBody(
    val status: Int,
    val token: String?,
    val error: String?
)