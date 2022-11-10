package com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary

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