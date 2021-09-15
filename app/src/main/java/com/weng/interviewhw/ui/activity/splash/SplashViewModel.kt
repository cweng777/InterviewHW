package com.weng.interviewhw.ui.activity.splash

import androidx.lifecycle.ViewModel
import com.weng.interviewhw.util.PreferencesManager

class SplashViewModel(
     preferencesManager: PreferencesManager
): ViewModel() {

    val token = preferencesManager.token
}