package com.giftpunding.osds.repository

import com.giftpunding.osds.repository.remote.datasource.AddressDataSource

class AddressRepository(private val addressDataSource: AddressDataSource) {
    suspend fun getAddress(apiKey: String, keyword: String) =
        addressDataSource.getAddressService().getAddress(apiKey, keyword)

    //좌표값으로 주소지( 지번 또는 도로명) 가져오기
    suspend fun getAddress(apiKey: String, x: String, y: String) =
        addressDataSource.getAddressService().getAddress(apiKey, x, y)
}