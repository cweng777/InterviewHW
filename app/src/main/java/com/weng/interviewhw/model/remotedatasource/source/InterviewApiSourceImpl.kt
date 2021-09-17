package com.weng.interviewhw.model.remotedatasource.source

import com.weng.interviewhw.extension.getResponseBody
import com.weng.interviewhw.model.remotedatasource.api.InterviewApi
import com.weng.interviewhw.model.remotedatasource.body.getuserinfo.GetUserInfoResponseBody
import com.weng.interviewhw.model.remotedatasource.body.register.RegisterResponseBody
import com.weng.interviewhw.model.remotedatasource.body.signin.SignInResponseBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InterviewApiSourceImpl(
    private val interviewApi: InterviewApi, //provided by retrofit
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): InterviewApiSource {

    /**
     * 註冊
     */
    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<RegisterResponseBody> {
        return flow {
            emit(
                interviewApi.register(firstName, lastName, email, password).getResponseBody()
            )
        }.flowOn(ioDispatcher)
    }

    /**
     * 登入
     */
    override suspend fun signIn(email: String, password: String): Flow<SignInResponseBody> {
        return flow {
            emit(
                interviewApi.login(email, password).getResponseBody()
            )
        }.flowOn(ioDispatcher)
    }

    /**
     * 擷取使用者資訊
     */
    override suspend fun getUserData(token: String): Flow<GetUserInfoResponseBody> {
        return flow {
            emit(
                interviewApi.getUserInfo(token).getResponseBody()
            )
        }.flowOn(ioDispatcher)
    }

}