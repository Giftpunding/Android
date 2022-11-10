package com.giftfunding.osds.data.repository.remote.datasource.dto.login

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("JWT Token") val jwtToken: String
)