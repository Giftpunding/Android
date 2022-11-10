package com.giftfunding.osds.data.repository.remote.datasource

import com.giftfunding.osds.data.repository.remote.service.LoginService
import retrofit2.Retrofit

class LoginRemoteDataSource(private val retrofit: Retrofit) {

    fun getLoginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }

}