package com.giftfunding.osds.repository

import com.giftfunding.osds.data.response.anniversary.AnniversaryRequest
import com.giftfunding.osds.data.response.anniversary.AnniversaryResponse
import com.giftfunding.osds.repository.remote.datasource.AnniversaryDataSource
import retrofit2.Response

class AnniversaryRepository(private val anniversaryDataSource: AnniversaryDataSource) {

    suspend fun addAnniversary(anniversaryDay: String, anniversary: String) : Response<AnniversaryResponse> {
        val anniversaryRequest = AnniversaryRequest(date = anniversaryDay, type = anniversary)
        return anniversaryDataSource.getAnniversaryService().postAnniversary(anniversaryRequest)
    }
}