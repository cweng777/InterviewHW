package com.weng.interviewhw.model.remotedatasource.body.register

import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("mail")
    val email: String,
    @SerializedName("passwd")
    val password: String
)