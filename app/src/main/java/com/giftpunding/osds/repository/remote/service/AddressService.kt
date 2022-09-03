package com.giftpunding.osds.repository.remote.service

import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.data.response.anniversary.AnniversaryRequest
import com.giftpunding.osds.data.response.anniversary.AnniversaryResponse
import com.giftpunding.osds.data.response.user.User
import retrofit2.Response
import retrofit2.http.*

//레트로핏 인터페이스 선언
interface AddressService {
    @GET("/v2/local/search/address.json")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): AddressSearchResultResponse

    //좌표값으로 주소지( 지번 또는 도로명) 가져오기
    @GET("/v2/local/geo/coord2address.json")
    suspend fun getAddress(
        @Header("Authorization") token: String,
        @Query("x") x: String,
        @Query("y") y: String,
    ): AddressSearchResultResponse

    @PATCH("api/user/me")
    suspend fun postAddress(
        @Query("address") address:String
    ):Response<User>
}