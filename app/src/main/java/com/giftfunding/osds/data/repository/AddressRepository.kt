package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource

class AddressRepository(private val addressDataSource: AddressDataSource) {

    //주소 검색
    suspend fun getAddress(apiKey: String, keyword: String, page: Int) =
        addressDataSource.getKakaoAddressService().getAddress(apiKey, keyword, page)

    suspend fun addAddress(address: String) = addressDataSource.getAddress().postAddress(address)
}