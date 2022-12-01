package com.giftfunding.osds.domain.login

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

interface LoginRepository {
    suspend fun getUserJWTWithRefreshToken(refreshToken : String) : LoginJwtDto
    suspend fun getUserJWTWithKakao(kakaoAccessToken : String) : LoginJwtDto

    fun getUserAccessToken() : String
    fun getUserRefreshToken() : String
}