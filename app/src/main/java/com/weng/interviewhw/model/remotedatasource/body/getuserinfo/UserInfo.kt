package com.weng.interviewhw.model.remotedatasource.body.getuserinfo


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("mail")
    val email: String?
)