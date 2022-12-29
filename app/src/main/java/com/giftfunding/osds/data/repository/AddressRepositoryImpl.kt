package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.KakaoAddressSearchResultResponse
import com.giftfunding.osds.domain.address.AddressRepository
import com.giftfunding.osds.domain.base.dto.UnitDto
import retrofit2.Response

class AddressRepositoryImpl(private val addressDataSource: AddressDataSource) : AddressRepository {
    override suspend fun getAddressWithKakao(
        keyword: String,
        page: Int
    ): KakaoAddressSearchResultResponse =
        addressDataSource.getKakaoAddressService().getAddressWithKakao(keyword, page)

    override suspend fun addUserAddress(value: String, detail: String, memo: String): UnitDto {
        val request = AddressRequestDto(mainAddress = value, detailAddress = detail, additionalInfo = memo)
        val response = addressDataSource.getAddressService().postUserAddress(request)

        return unitToDomainMapper(response)
    }

    // 코드 중복 됨, 한 곳으로 빼야하지 않을까 싶음.
    private fun unitToDomainMapper(response: Response<Unit>) : UnitDto {
        return UnitDto(
            code = response.code(),
            message = response.message()
        )
    }
}