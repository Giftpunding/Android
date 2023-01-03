package com.giftfunding.osds.data.repository.remote.datasource

import com.giftfunding.osds.data.repository.remote.service.AddressService
import retrofit2.Retrofit

//레트로핏 객체 반환
class AddressDataSource(private val kakaoRetrofit: Retrofit, private val retrofit: Retrofit) {
    fun getKakaoAddressService(): AddressService {
        return kakaoRetrofit.create(AddressService::class.java)
    }

    fun getAddressService(): AddressService {
        return retrofit.create(AddressService::class.java)
    }
}
