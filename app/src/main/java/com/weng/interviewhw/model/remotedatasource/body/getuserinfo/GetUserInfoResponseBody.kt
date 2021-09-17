package com.weng.interviewhw.model.remotedatasource.body.getuserinfo


import com.google.gson.annotations.SerializedName

/**
 * 使用者資料, retrofit 呼 api 完 放資料於此
 */
data class GetUserInfoResponseBody(
    @SerializedName("error")
    val error: String?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_info")
    val userInfo: UserInfo?
)