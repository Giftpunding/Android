package com.giftpunding.osds.repository.remote.service

import com.giftpunding.osds.data.response.anniversary.AnniversaryRequest
import com.giftpunding.osds.data.response.anniversary.AnniversaryResponse
import com.giftpunding.osds.data.response.login.LoginResponse
import com.giftpunding.osds.data.response.user.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AnniversaryService {

    @POST("/api/anniversaries")
    suspend fun postAnniversary(
        @Body anniversaryRequest: AnniversaryRequest
    ): Response<AnniversaryResponse>
}