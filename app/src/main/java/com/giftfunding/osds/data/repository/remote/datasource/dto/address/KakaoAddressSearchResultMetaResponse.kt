package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName

data class KakaoAddressSearchResultMetaResponse(
    @SerializedName("total_count")
    var totalCount: Int?,
    @SerializedName("pageable_count")
    var pageableCount: Int?,
    @SerializedName("is_end")
    var isEnd: Boolean?,
    @SerializedName("same_name")
    var sameName: KakaoRegionInfoReseponse
)
