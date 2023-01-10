package com.giftfunding.osds.domain.merchandise.dto

// 최대한 도메인 레이어에서 내려주는거를 활용
// UI는 model로 변환
data class Merchandise(
    val id: Long,
    val img: String,
    val brandTitle: String,
    val title: String,
    val price: Int,
    val mainCategory : String,
    val subCategory : String,
    val description: MerchandiseDescription,
    val info: MerchandiseInfo
) {
    // 재껴
    data class MerchandiseOption(
        val option : List<String>? = null
    ){
        data class MerchandiseColorOption(
            val color : List<String>? = null
        )
    }

    data class MerchandiseDescription(
        val infoImg: String
    )

    data class MerchandiseInfo(
        val detailInfo: MerchandiseDetailInfo,
        val sellerInfo: MerchandiseSellerInfo,
        val deliveryInfo: MerchandiseDeliveryInfo,
        val refundInfo: MerchandiseRefundInfo
    ) {
        data class MerchandiseDetailInfo(
            val id: String
        )

        data class MerchandiseSellerInfo(
            val sellerName: String
        )

        data class MerchandiseDeliveryInfo(
            val deliveryFee: Int,
            val additionalDeliveryAddress: String? = null,
            val additionalDeliveryFee: Int? = null,
        )

        data class MerchandiseRefundInfo(
            val refundReason: List<String>? = null,
            val refundFee: Int? = null,
            val refundAddress: String? = null,
            val refundPeriod: String? = null
        )
    }
}

