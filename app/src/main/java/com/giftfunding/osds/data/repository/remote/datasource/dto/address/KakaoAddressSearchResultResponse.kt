package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KakaoAddressSearchResultResponse(
    @SerializedName("meta")
    var meta : KakaoAddressSearchResultMetaResponse?,
    @SerializedName("documents")
    var documents : List<KakaoAddressSearchResultDocumentResponse>?
):Serializable
