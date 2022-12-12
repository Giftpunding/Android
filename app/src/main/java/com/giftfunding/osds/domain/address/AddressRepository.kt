package com.giftfunding.osds.domain.address

import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressSearchResultResponse

interface AddressRepository {
    suspend fun getAddress(apiKey: String, keyword: String, page: Int) : AddressSearchResultResponse
}