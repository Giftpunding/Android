package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.remote.datasource.AnniversaryDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryRequest
import com.giftfunding.osds.data.repository.remote.datasource.dto.base.toDomain
import com.giftfunding.osds.domain.anniversary.AnniversaryRepository
import com.giftfunding.osds.domain.base.dto.UnitDto
import retrofit2.Response

class AnniversaryRepositoryImpl(
    private val anniversaryDataSource: AnniversaryDataSource
) : AnniversaryRepository {

    /**
     * 성공시 204 코드만 내려옴
     * 그 이외의 데이터 X
     * 성공 실패는 code 값으로 판별
     */
    override suspend fun addAnniversary(name: String, date: String): UnitDto {
        val response = anniversaryDataSource.getAnniversaryService()
            .postAnniversary(anniversaryRequest = AnniversaryRequest(name, date))

        return response.toDomain()
    }
}


