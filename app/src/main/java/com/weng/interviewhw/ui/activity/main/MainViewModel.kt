package com.weng.interviewhw.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weng.interviewhw.util.PreferencesManager
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferencesManager: PreferencesManager
): ViewModel() {

    private val _logoutState : MutableLiveData<Boolean> = MutableLiveData(false)
    val logoutState : LiveData<Boolean> get() = _logoutState

    /**
     * 登出
     */
    fun logout() {
        viewModelScope.launch {
            preferencesManager.clearToken()
            _logoutState.value = true
        }
    }
}