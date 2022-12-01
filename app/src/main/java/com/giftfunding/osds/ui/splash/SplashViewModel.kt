package com.giftfunding.osds.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application.Companion.loginUseCase
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.domain.login.LoginUseCase
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _userAccessToken = MutableLiveData<String>()
    val userAccessToken: LiveData<String> get() = _userAccessToken

    private val _userRefreshToken = MutableLiveData<String>()
    val userRefreshToken: LiveData<String> get() = _userRefreshToken

    private val _userToken = MutableLiveData<ViewState<String>>()
    val userToken : LiveData<ViewState<String>> get() = _userToken

    fun getUserAccessToken() {
        _userAccessToken.postValue(loginUseCase.getUserAccessToken())
    }

    fun getUserRefreshToken() {
        _userRefreshToken.postValue(loginUseCase.getUserRefreshToken())
    }

    fun getTokenWithRefreshToken(refreshToken: String) = viewModelScope.launch {
        _userToken.value = ViewState.Loading()
        try {
            val response = loginUseCase.getUserJwtWithRefreshToken(refreshToken)
            _userToken.value = ViewState.Success(response.accessToken!!)
        } catch (e: Exception) {
            _userToken.value = ViewState.Error(e.message)
        }
    }
}