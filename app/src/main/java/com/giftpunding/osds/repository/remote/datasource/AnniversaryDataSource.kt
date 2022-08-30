package com.giftpunding.osds.repository.remote.datasource

import com.giftpunding.osds.repository.remote.service.AnniversaryService
import retrofit2.Retrofit

class AnniversaryDataSource(private val retrofit: Retrofit) {
    fun getAnniversaryService(): AnniversaryService {
        return retrofit.create(AnniversaryService::class.java)
    }
}