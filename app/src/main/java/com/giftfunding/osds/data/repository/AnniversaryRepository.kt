package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryRequest
import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryResponse
import com.giftfunding.osds.data.repository.remote.datasource.AnniversaryDataSource
import retrofit2.Response

class AnniversaryRepository(private val anniversaryDataSource: AnniversaryDataSource) {

    suspend fun addAnniversary(anniversaryDay: String, anniversary: String) : Response<AnniversaryResponse> {
        val anniversaryRequest =
            AnniversaryRequest(
                date = anniversaryDay,
                type = anniversary
            )
        return anniversaryDataSource.getAnniversaryService().postAnniversary(anniversaryRequest)
    }
}