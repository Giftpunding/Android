package com.giftpunding.osds.ui.address

import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse

interface ItemClickListener {
    fun clickAddressName(addressName: AddressSearchResultDocumentResponse)
    fun clickDetailAddressName(addressResult: AddressSearchResultDocumentResponse)
}