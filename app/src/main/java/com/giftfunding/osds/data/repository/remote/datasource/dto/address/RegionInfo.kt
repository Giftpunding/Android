package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName

data class RegionInfo(
    @SerializedName("region")
    var region: List<String>,
    @SerializedName("keyword")
    var keyword: String?,
    @SerializedName("selected_region")
    var selectedRegion: String?
)
