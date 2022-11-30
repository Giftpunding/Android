package com.giftfunding.osds.data.repository.remote.datasource.dto

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName("message")
    var message : String? = null,
    @SerializedName("code")
    var code : String? = null,
    @SerializedName("data")
    var data : T? = null
)