package com.giftfunding.osds.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application.Companion.loginUseCase
import com.giftfunding.osds.base.ViewState
import kotlinx.coroutines.launch

// 유즈케이스는 application에서 가져와서 쓴다.
class LoginViewModel : ViewModel() {

    private val _checkUserAccessToken = MutableLiveData<ViewState<Boolean>>()
    val checkUserAccessToken get() = _checkUserAccessToken

    fun getUserJwt(kakaoToken: String) = viewModelScope.launch {
        _checkUserAccessToken.value = ViewState.Loading()
        try {
            val loginStatus = loginUseCase.getUserJwtWithKakao(kakaoToken)
            if(loginStatus.accessToken?.isEmpty() == true){
                _checkUserAccessToken.value = ViewState.Error(loginStatus.message)
            }else{
                _checkUserAccessToken.value = ViewState.Success(true)
            }
        } catch (e: Exception) {
            _checkUserAccessToken.value = ViewState.Error(e.message, false)
        }
    }
}