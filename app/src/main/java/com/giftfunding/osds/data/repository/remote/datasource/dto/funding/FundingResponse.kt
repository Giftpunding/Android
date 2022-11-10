package com.giftfunding.osds.data.repository.remote.datasource.dto.funding

data class FundingResponse(
    val brand: String,
    val name: String,
    val img: String,
    val possibleFunding: Int,
    val nowFunding: Int,
    val totalPrice: Int
)