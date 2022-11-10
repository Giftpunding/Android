package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.address.AddressSearchResultResponse
import com.giftfunding.osds.data.repository.remote.datasource.dto.user.User
import retrofit2.Response
import retrofit2.http.*

//레트로핏 인터페이스 선언
interface AddressService {

    @GET("/v2/local/search/address.json")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): AddressSearchResultResponse

    @PATCH("api/user/me")
    suspend fun postAddress(
        @Query("address") address: String
    ): Response<User>
}