package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressSearchResultResponse(
    @SerializedName("meta")
    var meta : AddressSearchResultMetaResponse?,
    @SerializedName("documents")
    var documents : List<AddressSearchResultDocumentResponse>?
):Serializable
