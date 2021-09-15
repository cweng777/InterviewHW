package com.weng.interviewhw.model.repository

import com.weng.interviewhw.model.data.getuserinfo.GetUserInfoResult
import com.weng.interviewhw.model.data.register.RegisterResult
import com.weng.interviewhw.model.data.signin.SignInResult
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {
    /**
     * 註冊
     */
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<RegisterResult>

    /**
     * 登入
     */
    suspend fun signIn(
        email: String,
        password: String
    ): Flow<SignInResult>

    /**
     * 擷取使用者資訊
     */
    suspend fun getUserData(
        token: String
    ): Flow<GetUserInfoResult>
}