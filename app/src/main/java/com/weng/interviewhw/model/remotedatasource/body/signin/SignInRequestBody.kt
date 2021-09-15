package com.weng.interviewhw.model.remotedatasource.body.signin

import com.google.gson.annotations.SerializedName

data class SignInRequestBody(
    @SerializedName("mail")
    val email: String,
    @SerializedName("passwd")
    val password: String
)