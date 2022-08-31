package com.giftpunding.osds.repository

import com.giftpunding.osds.data.response.user.User
import com.giftpunding.osds.repository.remote.datasource.AnniversaryDataSource

class AnniversaryRepository(private val anniversaryDataSource: AnniversaryDataSource) {

    suspend fun addAnniversary(anniversaryDay: String, anniversary: String) {
        val userDto = User(
            anniversaryDay = anniversaryDay,
            anniversary = anniversary
        )
        anniversaryDataSource.getAnniversaryService().postUser(userDto)
    }
}