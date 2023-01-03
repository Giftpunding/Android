package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName

data class AddressRequestDto(
    @SerializedName("value")
    val mainAddress: String,
    @SerializedName("detail")
    val detailAddress: String,
    @SerializedName("memo")
    val additionalInfo: String
)