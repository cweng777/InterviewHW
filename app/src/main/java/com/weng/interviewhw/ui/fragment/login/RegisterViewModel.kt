package com.weng.interviewhw.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weng.interviewhw.model.data.register.ui.RegisterResultUI
import com.weng.interviewhw.model.data.share.Status
import com.weng.interviewhw.model.repository.InterviewRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val interviewRepository: InterviewRepository
): ViewModel() {

    private val _registerState : MutableLiveData<RegisterResultUI> = MutableLiveData()
    val registerState : LiveData<RegisterResultUI> get() = _registerState

    /**
     * 註冊
     */
    fun register(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            interviewRepository.register(firstName, lastName, email, password)
                .onStart { _registerState.value = RegisterResultUI.Loading }
                .catch { _registerState.value = RegisterResultUI.Failure(it.message?:"register error!!") }
                .collect { registerResult ->
                    when (registerResult.status) {
                        Status.Success -> {
                            _registerState.value = RegisterResultUI.Success
                        }
                        Status.Failure -> {
                            _registerState.value = RegisterResultUI.Failure(registerResult.error)
                        }
                    }
                }
        }
    }
}