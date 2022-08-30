package com.giftpunding.osds.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("JWT Token") val jwtToken: String
)