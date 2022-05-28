package com.giftpunding.osds.data.response.home.merchandise

import java.io.Serializable

data class MerchandiseResponse(
    val brand: String,
    val name: String,
    val price: Int,
    val img: String
) : Serializable