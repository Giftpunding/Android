package com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnniversaryRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("date")
    var date: String
) : Serializable
