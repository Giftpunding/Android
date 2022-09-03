package com.giftpunding.osds.ui.address

import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse

interface AddressSearchDetailClickListener {
    fun addressSearchDetailClickable(addressResult: AddressSearchResultDocumentResponse)
}