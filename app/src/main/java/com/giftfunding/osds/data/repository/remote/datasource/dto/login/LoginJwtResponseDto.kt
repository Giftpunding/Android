package com.giftfunding.osds.data.repository.remote.datasource.dto.login

import com.google.gson.annotations.SerializedName

data class LoginJwtResponseDto(
    @SerializedName("grantType")
    var grantType: String?,
    @SerializedName("accessToken")
    var accessToken: String?,
    @SerializedName("accessTokenExpireTime")
    var accessTokenExpireTime: String?,
    @SerializedName("refreshToken")
    var refreshToken: String?,
    @SerializedName("refreshTokenExpireTime")
    var refreshTokenExpireTime: String?,
)