package com.giftfunding.osds.data.repository.remote.datasource.dto.login

import com.google.gson.annotations.SerializedName

data class LoginRefreshTokenDto(
    @SerializedName("refresh")
    var refreshToken : String?
)
