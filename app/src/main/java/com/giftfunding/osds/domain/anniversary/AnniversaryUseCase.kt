package com.giftfunding.osds.domain.anniversary

import com.giftfunding.osds.data.repository.AnniversaryRepositoryImpl
import com.giftfunding.osds.domain.base.dto.UnitDto

class AnniversaryUseCase(
    private val anniversaryRepository: AnniversaryRepositoryImpl
) {
    // 기념일 종류, 기념일 날짜
    suspend fun addAnniversary(name: String, date: String): UnitDto =
        anniversaryRepository.addAnniversary(name, date)

}