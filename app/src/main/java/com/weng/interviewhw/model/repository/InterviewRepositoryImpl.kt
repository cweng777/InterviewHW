package com.weng.interviewhw.model.repository

import com.weng.interviewhw.model.data.getuserinfo.GetUserInfoResult
import com.weng.interviewhw.model.data.getuserinfo.UserInfoResult
import com.weng.interviewhw.model.data.register.RegisterResult
import com.weng.interviewhw.model.data.share.Status
import com.weng.interviewhw.model.data.signin.SignInResult
import com.weng.interviewhw.model.remotedatasource.source.InterviewApiSource
import com.weng.interviewhw.util.PreferencesManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class InterviewRepositoryImpl(
    private val interviewApiSource: InterviewApiSource,
    private val preferencesManager: PreferencesManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
): InterviewRepository {
    /**
     * 註冊
     */
    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<RegisterResult> {
        return interviewApiSource.register(
            firstName, lastName, email, password
        ).map { registerResponseBody ->
            RegisterResult(
                status = if (registerResponseBody.status == 0) {
                    preferencesManager.saveToken(registerResponseBody.token?:"")
                    Status.Success
                } else {
                    Status.Failure
                },
                token = registerResponseBody.token?:"",
                error = registerResponseBody.error?:""
            )
        }.flowOn(defaultDispatcher)
    }

    /**
     * 登入
     */
    override suspend fun signIn(email: String, password: String): Flow<SignInResult> {
        return interviewApiSource.signIn(
            email, password
        ).map { signInResponseBody ->
            SignInResult(
                status = if (signInResponseBody.status == 0) {
                    preferencesManager.saveToken(signInResponseBody.token?:"")
                    Status.Success
                } else {
                    Status.Failure
                },
                token = signInResponseBody.token?:"",
                error = signInResponseBody.error?:""
            )
        }.flowOn(defaultDispatcher)
    }

    /**
     * 擷取使用者資訊
     */
    override suspend fun getUserData(token: String): Flow<GetUserInfoResult> {
        return interviewApiSource.getUserData(
            token
        ).map { getUserInfoResponseBody ->
            GetUserInfoResult(
                status = if (getUserInfoResponseBody.status == 0) {
                    Status.Success
                } else {
                    Status.Failure
                },
                error = getUserInfoResponseBody.error?:"",
                userInfo = UserInfoResult(
                    email = getUserInfoResponseBody.userInfo?.email?:"",
                    firstName = getUserInfoResponseBody.userInfo?.firstName?:"",
                    lastName = getUserInfoResponseBody.userInfo?.lastName?:""
                )
            )
        }
    }
}