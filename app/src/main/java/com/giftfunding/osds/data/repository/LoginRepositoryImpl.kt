package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtWithRefreshTokenDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.mapper.toDomain
import com.giftfunding.osds.domain.login.LoginRepository
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

class LoginRepositoryImpl(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun getUserJWTWithRefreshToken(refreshToken: String): LoginJwtDto {
        val request = LoginJwtWithRefreshTokenDto(refreshToken)
        val response = loginRemoteDataSource.getLoginService().refreshAccessToken(request)

        loginSharedPreference.refreshToken = response.refreshToken!!
        loginSharedPreference.accessToken = response.accessToken!!
        return response.toDomain()
    }

    override suspend fun getUserJWTWithKakao(kakaoAccessToken: String): LoginJwtDto {
        val request = LoginJwtRequestDto(kakaoAccessToken)
        val response = loginRemoteDataSource.getLoginService().loginWithKakao(request)

        loginSharedPreference.refreshToken = response.refreshToken!!
        loginSharedPreference.accessToken = response.accessToken!!
        return response.toDomain()
    }

    override fun getUserAccessToken(): String {
        return loginSharedPreference.accessToken ?: ""
    }

    override fun getUserRefreshToken(): String {
        return loginSharedPreference.refreshToken ?: ""
    }

    override fun deleteUserAccessToken() {
        loginSharedPreference.accessToken = ""
    }

    override fun deleteUserRefreshToken() {
        loginSharedPreference.refreshToken = ""
    }
}