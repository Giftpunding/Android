package com.giftfunding.osds.domain.address

import com.giftfunding.osds.data.repository.remote.datasource.dto.address.KakaoAddressSearchResultResponse
import com.giftfunding.osds.domain.base.dto.UnitDto

interface AddressRepository {
    suspend fun getAddressWithKakao(keyword: String, page: Int) : KakaoAddressSearchResultResponse
    suspend fun addUserAddress(value : String, detail : String, memo : String) : UnitDto
}