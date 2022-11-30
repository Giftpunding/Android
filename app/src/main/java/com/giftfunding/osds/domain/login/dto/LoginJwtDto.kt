package com.giftfunding.osds.domain.login.dto

data class LoginJwtDto(
    //JWT
    var message : String?,
    var code : String?,
    var accessToken : String?
)
