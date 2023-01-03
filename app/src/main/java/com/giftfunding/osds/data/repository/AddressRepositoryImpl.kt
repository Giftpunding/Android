package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.KakaoAddressSearchResultResponse
import com.giftfunding.osds.data.repository.remote.datasource.dto.base.toDomain
import com.giftfunding.osds.domain.address.AddressRepository
import com.giftfunding.osds.domain.base.dto.UnitDto


class AddressRepositoryImpl(private val addressDataSource: AddressDataSource) : AddressRepository {
    override suspend fun getAddressWithKakao(
        keyword: String,
        page: Int
    ): KakaoAddressSearchResultResponse =
        addressDataSource.getKakaoAddressService().getAddressWithKakao(keyword, page)

    override suspend fun addUserAddress(value: String, detail: String, memo: String): UnitDto {
        val request = AddressRequestDto(mainAddress = value, detailAddress = detail, additionalInfo = memo)
        val response = addressDataSource.getAddressService().postUserAddress(request)

        return response.toDomain()
    }
}