package com.weng.interviewhw.di

import com.weng.interviewhw.util.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val generalModule = module {
    single { PreferencesManager(androidContext()) }
}