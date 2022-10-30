package com.giftfunding.osds.repository

import com.giftfunding.osds.repository.remote.datasource.AddressDataSource

class AddressRepository(private val addressDataSource: AddressDataSource) {

    //주소 검색
    suspend fun getAddress(apiKey: String, keyword: String, page: Int) =
        addressDataSource.getKakaoAddressService().getAddress(apiKey, keyword, page)

    suspend fun addAddress(address: String) = addressDataSource.getAddress().postAddress(address)
}