package com.giftpunding.osds.repository.remote.service

import com.giftpunding.osds.data.response.anniversary.AnniversaryRequest
import com.giftpunding.osds.data.response.anniversary.AnniversaryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnniversaryService {
    //기존 Call을 받아 enqueue -> CallBack 구현이 아닌 Response로 받음
    //onSuccess, onFailure 같은 override 함수 재정의 필요 없음
    @POST("/api/anniversaries")
    suspend fun postAnniversary(
        @Body anniversaryRequest: AnniversaryRequest
    ): Response<AnniversaryResponse>
}