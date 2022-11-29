package com.giftfunding.osds.domain.login

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginRefreshTokenDto
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

interface LoginRepository {
    fun refreshAccessToken(token : LoginRefreshTokenDto) : LoginJwtResponseDto
    suspend fun getUserJWTWithKakao(token : String) : LoginJwtDto
}