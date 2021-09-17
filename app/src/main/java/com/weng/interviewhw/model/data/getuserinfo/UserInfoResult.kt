package com.weng.interviewhw.model.data.getuserinfo


/**
 * 使用者基本資料, repository傳輸用的Data
 */
data class UserInfoResult(
    val firstName: String,
    val lastName: String,
    val email: String
)
