package com.giftfunding.osds.data.repository

import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginRefreshTokenDto
import com.giftfunding.osds.domain.login.LoginRepository
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

class LoginRepositoryImpl(
    private val loginSharedPreference: LoginSharedPreference,
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun refreshAccessToken(token: LoginRefreshTokenDto): LoginJwtResponseDto {
        val response = loginRemoteDataSource.getLoginService().refreshAccessToken(token)

    }

    override fun getUserJWTWithKakao(token: String): LoginJwtDto {
        val response = loginRemoteDataSource.getLoginService()
            .loginWithKakao(LoginJwtRequestDto(kakaoAccessToken = token))

        if (response.isSuccessful) {
            val result = response.body()?.data
            loginSharedPreference.setRefreshToken(result?.refreshToken!!)
            loginSharedPreference.setUserToken(result.accessToken!!)
        }
        return response.body()?.toDomainModel()!!
    }

    companion object {
        private const val TAG: String = "LoginRepository ...."
    }
}