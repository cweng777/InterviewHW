package com.weng.interviewhw.ui.fragment.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weng.interviewhw.model.data.getuserinfo.ui.GetUserInfoResultUI
import com.weng.interviewhw.model.data.getuserinfo.ui.UserInfoUI
import com.weng.interviewhw.model.repository.InterviewRepository
import com.weng.interviewhw.util.PreferencesManager
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WalletViewModel(
    private val interviewRepository: InterviewRepository,
    private val preferencesManager: PreferencesManager
): ViewModel() {

    private val _userInfoResult : MutableLiveData<GetUserInfoResultUI> = MutableLiveData()
    val userInfoResult : LiveData<GetUserInfoResultUI> get() = _userInfoResult

    private val _logoutState : MutableLiveData<Boolean> = MutableLiveData(false)
    val logoutState : LiveData<Boolean> get() = _logoutState

    /**
     * 取得使用者資訊
     */
    fun getUserInfo() {
        viewModelScope.launch {
            val token = preferencesManager.token.first()
            token?.let {
                interviewRepository.getUserData(it)
                    .onStart { _userInfoResult.value = GetUserInfoResultUI.Loading }
                    .catch {
                        _userInfoResult.value =
                            GetUserInfoResultUI.Failure(it.message ?: "get user info error!!")
                    }
                    .collect { GetUserInfoResult ->
                        _userInfoResult.value = GetUserInfoResultUI.Success(
                            UserInfoUI(
                                email = GetUserInfoResult.userInfo.email,
                                firstName = GetUserInfoResult.userInfo.firstName,
                                lastName = GetUserInfoResult.userInfo.lastName
                            )
                        )
                    }
            }
        }
    }

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