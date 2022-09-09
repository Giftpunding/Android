package com.giftfunding.osds.data.response.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("account")
    var account: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("nickname")
    var nickname: String?,
    @SerializedName("phoneNumber")
    var phoneNumber: String?,
    @SerializedName("profile")
    var profile: String?
)

