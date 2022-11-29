package com.giftfunding.osds.data.repository

import android.util.Log
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginRefreshTokenDto
import com.giftfunding.osds.domain.login.LoginRepository
import com.giftfunding.osds.domain.login.dto.LoginJwtDto
import kotlin.math.log

class LoginRepositoryImpl(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun refreshAccessToken(token: LoginRefreshTokenDto): LoginJwtResponseDto {
        val response = loginRemoteDataSource.getLoginService().refreshAccessToken(token)

        loginSharedPreference.setRefreshToken(response.refreshToken!!)
        loginSharedPreference.setUserToken(response.accessToken!!)
        return response
    }

    override suspend fun getUserJWTWithKakao(token: String): LoginJwtDto {
        val request = LoginJwtRequestDto(token)
        val response = loginRemoteDataSource.getLoginService().loginWithKakao(request)

        loginSharedPreference.setRefreshToken(response.refreshToken!!)
        loginSharedPreference.setUserToken(response.accessToken!!)
        return response.toDomainModel()
    }
}