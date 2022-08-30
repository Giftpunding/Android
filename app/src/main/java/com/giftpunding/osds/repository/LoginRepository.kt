package com.giftpunding.osds.repository

import android.util.Log
import com.giftpunding.osds.data.response.BaseResponse
import com.giftpunding.osds.data.response.login.LoginResponse
import com.giftpunding.osds.repository.local.pref.LoginSharedPreference
import com.giftpunding.osds.repository.remote.datasource.LoginRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) {

    fun getJwt(accessToken: String) {
        loginRemoteDataSource.getLoginService().postLoginToken(accessToken).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginSharedPreference.setUserToken(response.body()!!.jwtToken)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

    companion object {
        private const val TAG: String = "LoginRepository ...."
    }
}