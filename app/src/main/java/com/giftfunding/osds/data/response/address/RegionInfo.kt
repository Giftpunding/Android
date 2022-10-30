package com.giftfunding.osds.data.response.address

import com.google.gson.annotations.SerializedName

data class RegionInfo(
    @SerializedName("region")
    var region: List<String>,
    @SerializedName("keyword")
    var keyword: String?,
    @SerializedName("selected_region")
    var selectedRegion: String?
)
