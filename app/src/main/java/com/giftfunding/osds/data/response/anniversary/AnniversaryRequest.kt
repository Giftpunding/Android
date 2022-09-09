package com.giftfunding.osds.data.response.anniversary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnniversaryRequest(
    @SerializedName("date")
    var date: String,
    @SerializedName("type")
    var type: String
) : Serializable
