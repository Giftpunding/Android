package com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnniversaryResponse (
    @SerializedName("code")
    var code : Int?,
    @SerializedName("message")
    var message: String?
):Serializable