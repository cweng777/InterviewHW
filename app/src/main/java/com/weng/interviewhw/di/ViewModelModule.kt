package com.weng.interviewhw.di

import com.weng.interviewhw.ui.activity.splash.SplashViewModel
import com.weng.interviewhw.ui.fragment.login.RegisterViewModel
import com.weng.interviewhw.ui.fragment.login.SignInViewModel
import com.weng.interviewhw.ui.fragment.wallet.WalletViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { WalletViewModel(get(), get()) }
}