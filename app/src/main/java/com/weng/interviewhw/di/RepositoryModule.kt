package com.weng.interviewhw.di

import com.weng.interviewhw.model.repository.InterviewRepository
import com.weng.interviewhw.model.repository.InterviewRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<InterviewRepository> { InterviewRepositoryImpl(get(), get()) }
}