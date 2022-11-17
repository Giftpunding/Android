package com.giftfunding.osds.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giftfunding.osds.application.Application.Companion.loginUseCase
import com.giftfunding.osds.base.ViewState

class LoginViewModel() : ViewModel() {

    private val _checkUserAccessToken = MutableLiveData<ViewState<Boolean>>()
    val checkUserAccessToken get() = _checkUserAccessToken

    fun getUserJwt(kakaoToken: String) {
        _checkUserAccessToken.value = ViewState.Loading()
        try {
            val loginStatus = loginUseCase.getUserJwtWithKakao(kakaoToken)
            if(loginStatus.accessToken?.isEmpty() == true){
                _checkUserAccessToken.value = ViewState.Error(loginStatus.message)
            }else{
                _checkUserAccessToken.value = ViewState.Success(true)
            }
        } catch (e: Exception) {
            _checkUserAccessToken.value = ViewState.Error(e.message, null)
        }
    }
}