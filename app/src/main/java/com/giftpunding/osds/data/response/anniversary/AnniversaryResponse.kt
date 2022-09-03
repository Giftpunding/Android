package com.giftpunding.osds.data.response.anniversary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnniversaryResponse (
    @SerializedName("id")
    var id : Int?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("type")
    var type: String?
):Serializable