package com.giftpunding.osds.repository.remote.datasource

import com.giftpunding.osds.repository.remote.service.AddressService
import com.giftpunding.osds.repository.remote.service.LoginService
import retrofit2.Retrofit

//레트로핏 객체 반환
class AddressDataSource(private val retrofit: Retrofit) {
    fun getAddressService(): AddressService {
        return retrofit.create(AddressService::class.java)
    }
}
