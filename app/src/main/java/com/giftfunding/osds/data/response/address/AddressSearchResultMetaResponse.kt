package com.giftfunding.osds.data.response.address

import com.google.gson.annotations.SerializedName

data class AddressSearchResultMetaResponse(
    @SerializedName("total_count")
    var totalCount: Int?,
    @SerializedName("pageable_count")
    var pageableCount: Int?,
    @SerializedName("is_end")
    var isEnd: Boolean?,
    @SerializedName("same_name")
    var sameName: RegionInfo
)
