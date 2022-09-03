package com.giftpunding.osds.repository.remote.datasource

import com.giftpunding.osds.repository.remote.service.AddressService
import com.giftpunding.osds.repository.remote.service.LoginService
import retrofit2.Retrofit

//레트로핏 객체 반환
class AddressDataSource(private val kakaoRetrofit: Retrofit, private val retrofit: Retrofit) {
    fun getKakaoAddressService(): AddressService {
        return kakaoRetrofit.create(AddressService::class.java)
    }

    fun getAddress(): AddressService {
        return retrofit.create(AddressService::class.java)
    }
}
