package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.address.KakaoAddressSearchResultResponse
import retrofit2.Response
import retrofit2.http.*

//레트로핏 인터페이스 선언
interface AddressService {
    @GET("/v2/local/search/address.json")
    suspend fun getAddressWithKakao(
        @Query("query") query: String,
        @Query("page") page: Int
    ): KakaoAddressSearchResultResponse

    @POST("/api/user/me/address")
    suspend fun postUserAddress(
        @Body address : AddressRequestDto
    ) : Response<Unit>
}