package com.weng.interviewhw.model.data.getuserinfo

import com.weng.interviewhw.model.data.share.Status

/**
 * 呼api拿到的使用者資訊, repository傳輸用的Data
 */
data class GetUserInfoResult(
    val error: String,
    val status: Status,
    val userInfo: UserInfoResult
)
