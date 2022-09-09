package com.giftfunding.osds.data.response.funding

data class FundingResultResponse(
    val img:String,
    val brand: String,
    val name: String,
    val price: Int,
    val paymentType: String,
    val paymentPrice: Int,
    val sender: String,
    val message: String
)