package com.weng.interviewhw.model.remotedatasource.body.signin

/**
 * 登入結果資料, retrofit 呼 api 完 放資料於此
 */
data class SignInResponseBody(
    val status: Int,
    val token: String?,
    val error: String?
)
