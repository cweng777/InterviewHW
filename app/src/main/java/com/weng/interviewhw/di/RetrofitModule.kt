package com.weng.interviewhw.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.weng.interviewhw.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single<Gson> {
        GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://inner.ixensor.com/")
            .client(createOkhttpClient(get()))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
}

private fun createOkhttpClient(application: Application): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if(BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(ChuckerInterceptor(application))
        }
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
    }.build()
}