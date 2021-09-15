package com.weng.interviewhw.di

import com.weng.interviewhw.ui.activity.main.MainViewModel
import com.weng.interviewhw.ui.activity.splash.SplashViewModel
import com.weng.interviewhw.ui.fragment.login.RegisterViewModel
import com.weng.interviewhw.ui.fragment.login.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
}