package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressSearchResultResponse
import com.giftfunding.osds.domain.address.AddressRepository

class AddressRepositoryImpl(private val addressDataSource: AddressDataSource) : AddressRepository {
    override suspend fun getAddress(
        keyword: String,
        page: Int
    ): AddressSearchResultResponse =
        addressDataSource.getKakaoAddressService().getAddress(keyword, page)

}