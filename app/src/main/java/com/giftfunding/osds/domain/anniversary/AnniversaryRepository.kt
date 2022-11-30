package com.giftfunding.osds.domain.anniversary

import com.giftfunding.osds.domain.base.dto.UnitDto

interface AnniversaryRepository {
    //기념일 종류와 기념일 날짜 저장
    suspend fun addAnniversary(name: String, date: String) : UnitDto
}