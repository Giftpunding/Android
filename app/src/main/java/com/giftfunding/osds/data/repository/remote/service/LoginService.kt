package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.BaseResponse
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginRefreshTokenDto
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {
    @POST("/api/login/kakao")
    suspend fun loginWithKakao(
        @Body kakaoToken: LoginJwtRequestDto
    ): LoginJwtResponseDto


    @POST("/api/token/refresh")
    fun refreshAccessToken(
        @Body refreshToken : LoginRefreshTokenDto
    ): LoginJwtResponseDto
}