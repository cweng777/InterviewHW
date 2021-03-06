package com.weng.interviewhw.model.remotedatasource.api

import com.weng.interviewhw.model.remotedatasource.body.getuserinfo.GetUserInfoResponseBody
import com.weng.interviewhw.model.remotedatasource.body.register.RegisterResponseBody
import com.weng.interviewhw.model.remotedatasource.body.signin.SignInResponseBody
import retrofit2.Response
import retrofit2.http.*

interface InterviewApi {
    /**
     * 註冊
     */
    @POST("InterviewAPI/android/interview.php?api=register")
    @FormUrlEncoded
    suspend fun register(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("mail") email: String,
        @Field("passwd") password: String
    ): Response<RegisterResponseBody>

    /**
     * 登入
     */
    @POST("InterviewAPI/android/interview.php?api=login")
    @FormUrlEncoded
    suspend fun login(
        @Field("mail") email: String,
        @Field("passwd") password: String
    ): Response<SignInResponseBody>

    /**
     * 擷取使用者資訊
     */
    @GET("https://inner.ixensor.com/InterviewAPI/android/interview.php?api=get_user_info")
    suspend fun getUserInfo(
        @Query("token") token: String
    ):Response<GetUserInfoResponseBody>
}