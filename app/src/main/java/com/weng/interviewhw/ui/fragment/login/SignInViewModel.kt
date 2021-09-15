package com.weng.interviewhw.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weng.interviewhw.model.data.share.Status
import com.weng.interviewhw.model.data.signin.ui.SignInResultUI
import com.weng.interviewhw.model.repository.InterviewRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SignInViewModel(
    private val interviewRepository: InterviewRepository
): ViewModel() {

    private val _signInState : MutableLiveData<SignInResultUI> = MutableLiveData()
    val signInState : LiveData<SignInResultUI> get() = _signInState

    /**
     * 登入
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            interviewRepository.signIn(email, password)
                .onStart { _signInState.value = SignInResultUI.Loading }
                .catch { _signInState.value = SignInResultUI.Failure(it.message?:"login error!!")}
                .collect { signInResult ->
                    when(signInResult.status) {
                        Status.Success -> {
                            _signInState.value = SignInResultUI.Success
                        }
                        Status.Failure -> {
                            _signInState.value = SignInResultUI.Failure(signInResult.error)
                        }
                    }
                }
        }
    }

}