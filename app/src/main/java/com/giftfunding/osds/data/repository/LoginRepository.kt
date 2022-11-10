package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginResponse
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
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