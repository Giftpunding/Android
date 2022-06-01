package com.giftpunding.osds.repository

import android.util.Log
import com.giftpunding.osds.data.response.BaseResponse
import com.giftpunding.osds.repository.local.pref.LoginSharedPreference
import com.giftpunding.osds.repository.remote.datasource.LoginRemoteDataSource
import retrofit2.Callback

class LoginRepository(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) {


}

