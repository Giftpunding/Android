package com.giftpunding.osds.ui.address

import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse

interface AddressSearchClickListener {
    fun addressSearchClickable(addressName: AddressSearchResultDocumentResponse)
}