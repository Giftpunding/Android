package com.giftfunding.osds.data.response.address

import java.io.Serializable

data class AddressSearchResultResponseTemp(
    val searchKeyword: String,
    val addressType: String,
    val address: String
) : Serializable
