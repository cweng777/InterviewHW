package com.weng.interviewhw.model.data.getuserinfo

import com.weng.interviewhw.model.data.share.Status


data class GetUserInfoResult(
    val error: String,
    val status: Status,
    val userInfo: UserInfoResult
)
