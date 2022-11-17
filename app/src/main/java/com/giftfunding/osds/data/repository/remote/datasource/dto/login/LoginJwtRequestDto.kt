package com.giftfunding.osds.data.repository.remote.datasource.dto.login

import com.google.gson.annotations.SerializedName

data class LoginJwtRequestDto(
    @SerializedName("kakaoAccessToken")
    var kakaoAccessToken : String?
)