package com.giftpunding.osds.repository.remote.datasource

import com.giftpunding.osds.repository.remote.service.LoginService
import retrofit2.Retrofit

class LoginRemoteDataSource(private val retrofit: Retrofit) {

    fun getLoginService(): LoginService{
        return retrofit.create(LoginService::class.java)
    }

}