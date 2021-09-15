package com.weng.interviewhw.model.remotedatasource.body.getuserinfo


import com.google.gson.annotations.SerializedName

data class GetUserInfoResponseBody(
    @SerializedName("error")
    val error: String?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("user_info")
    val userInfo: UserInfo?
)