package com.giftpunding.osds.data.response.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressSearchResultResponse(
    @SerializedName("meta")
    var meta : AddressSearchResultMetaResponse?,
    @SerializedName("documents")
    var documents : List<AddressSearchResultDocumentResponse>?
):Serializable
