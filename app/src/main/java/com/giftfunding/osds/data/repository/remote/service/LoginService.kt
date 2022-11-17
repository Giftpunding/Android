package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginRefreshTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {
    @POST("/api/user/login/token")
    fun loginWithKakao(
        @Body kakaoToken: LoginJwtRequestDto
    ): Response<BaseResponse<LoginJwtResponseDto>>


    @POST("/api/token/refresh")
    fun refreshAccessToken(
        @Body refreshToken : LoginRefreshTokenDto
    ): Response<LoginJwtResponseDto>
}