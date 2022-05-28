package com.giftpunding.osds.repository

import com.giftpunding.osds.data.response.BaseResponse
import com.giftpunding.osds.repository.local.pref.LoginSharedPreference
import com.giftpunding.osds.repository.remote.datasource.LoginRemoteDataSource
import retrofit2.Callback

class LoginRepository(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) {

    companion object {
        private const val TAG: String = "LoginRepository ...."
    }

}

