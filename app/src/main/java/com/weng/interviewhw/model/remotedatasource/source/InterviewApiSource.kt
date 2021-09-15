package com.weng.interviewhw.model.remotedatasource.source

import com.weng.interviewhw.model.remotedatasource.body.getuserinfo.GetUserInfoResponseBody
import com.weng.interviewhw.model.remotedatasource.body.register.RegisterResponseBody
import com.weng.interviewhw.model.remotedatasource.body.signin.SignInResponseBody
import kotlinx.coroutines.flow.Flow

interface InterviewApiSource {
    /**
     * 註冊
     */
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<RegisterResponseBody>

    /**
     * 登入
     */
    suspend fun signIn(
        email: String,
        password: String
    ): Flow<SignInResponseBody>

    /**
     * 擷取使用者資訊
     */
    suspend fun getUserData(
        token: String
    ): Flow<GetUserInfoResponseBody>
}