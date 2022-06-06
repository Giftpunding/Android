package com.giftpunding.osds.data.response.address

import java.io.Serializable

data class AddressSearchResultResponse(
    val searchKeyword: String,
    val addressType: String,
    val address: String
) : Serializable
