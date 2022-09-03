package com.giftpunding.osds.repository

import com.giftpunding.osds.data.response.anniversary.AnniversaryRequest
import com.giftpunding.osds.data.response.anniversary.AnniversaryResponse
import com.giftpunding.osds.data.response.user.User
import com.giftpunding.osds.repository.remote.datasource.AnniversaryDataSource
import retrofit2.Call
import retrofit2.Response

class AnniversaryRepository(private val anniversaryDataSource: AnniversaryDataSource) {

    suspend fun addAnniversary(anniversaryDay: String, anniversary: String) : Response<AnniversaryResponse> {
        val anniversaryRequest = AnniversaryRequest(date = anniversaryDay, type = anniversary)
        return anniversaryDataSource.getAnniversaryService().postAnniversary(anniversaryRequest)
    }
}