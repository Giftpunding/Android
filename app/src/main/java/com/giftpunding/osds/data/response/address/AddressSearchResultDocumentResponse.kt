package com.giftpunding.osds.data.response.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressSearchResultDocumentResponse(
    @SerializedName("address_name")
    var addressName: String?,

    @SerializedName("address_type")
    var addressType: String?,

    @SerializedName("x")
    var x: String?,

    @SerializedName("y")
    var y: String?,

    @SerializedName("address")
    var address: Address?,

    @SerializedName("road_address")
    var roadAddress: RoadAaddress?
) : Serializable
