package com.giftfunding.osds.domain.login

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtRequestDto
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    //프레젠테이션 레이어에서 데이터를 요청
    suspend fun getUserJwtWithKakao(accessToken: String) : LoginJwtDto{
        return loginRepository.getUserJWTWithKakao(accessToken)
    }

    suspend fun getUserJwtWithRefreshToken(refreshToken : String) : LoginJwtDto{
        return loginRepository.getUserJWTWithRefreshToken(refreshToken)
    }

    fun getUserAccessToken() : String{
        return loginRepository.getUserAccessToken()
    }

    fun getUserRefreshToken() : String{
        return loginRepository.getUserRefreshToken()
    }
}
