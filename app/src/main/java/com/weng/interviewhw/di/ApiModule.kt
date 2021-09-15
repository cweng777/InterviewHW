package com.weng.interviewhw.di

import com.weng.interviewhw.model.remotedatasource.api.InterviewApi
import com.weng.interviewhw.model.remotedatasource.source.InterviewApiSource
import com.weng.interviewhw.model.remotedatasource.source.InterviewApiSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<InterviewApi> { get<Retrofit>().create(InterviewApi::class.java) }
    single<InterviewApiSource> { InterviewApiSourceImpl(get()) }
}