package com.giftfunding.osds.domain.address

import com.giftfunding.osds.data.repository.remote.datasource.dto.address.KakaoAddressSearchResultResponse

class AddressUseCase(
    private val addressRepository: AddressRepository
) {
    suspend fun addUserAddress(value: String, detail: String, memo: String) =
        addressRepository.addUserAddress(value, detail, memo)

    suspend fun getAddressWithKakao(keyword : String, page : Int) : KakaoAddressSearchResultResponse{
        return addressRepository.getAddressWithKakao(keyword, page)
    }
}