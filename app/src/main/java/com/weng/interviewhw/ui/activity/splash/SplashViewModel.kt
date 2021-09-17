package com.weng.interviewhw.ui.activity.splash

import androidx.lifecycle.ViewModel
import com.weng.interviewhw.util.PreferencesManager

class SplashViewModel(
     preferencesManager: PreferencesManager
): ViewModel() {

    //取得登入token
    val token = preferencesManager.token
}