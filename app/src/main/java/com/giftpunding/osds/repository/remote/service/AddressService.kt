package com.giftpunding.osds.repository.remote.service

import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.data.response.login.LoginResponse
import com.giftpunding.osds.data.response.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

//레트로핏 인터페이스 선언
interface AddressService {
    @GET("/v2/local/search/address.json")
    fun getAddress(
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<AddressSearchResultResponse>
}