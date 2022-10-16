package com.giftfunding.osds.data.response.address

data class AddressKeywordSearchResultResponse(
    var documents: List<AddressKeywordSearchResultDocumentResponse>,
    var meta: AddressKeywordSearchResultMetaResponse
)

data class AddressKeywordSearchResultDocumentResponse(
    var address_name: String,
    var id: String,
    var place_name: String,
    var place_url: String,
    var road_address_name: String,
)

data class AddressKeywordSearchResultMetaResponse(
    var is_end: Boolean,
    var pageable_count: Int,
    var total_count: Int
)