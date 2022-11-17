package com.giftfunding.osds.data.repository.remote.datasource.dto.user

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String?,
    @SerializedName("email")
    var email : String?,
    @SerializedName("profileImage")
    var profileImage : String?
)
