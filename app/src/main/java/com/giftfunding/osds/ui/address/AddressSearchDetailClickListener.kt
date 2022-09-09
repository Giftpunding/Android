package com.giftfunding.osds.ui.address

import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse

interface AddressSearchDetailClickListener {
    fun addressSearchDetailClickable(addressResult: AddressSearchResultDocumentResponse)
}