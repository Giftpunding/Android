package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryRequest
import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnniversaryService {
    @POST("/api/user/anniversaries")
    suspend fun postAnniversary(
        @Body anniversaryRequest: AnniversaryRequest
    ):Response<Unit>
}