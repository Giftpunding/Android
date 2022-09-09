package com.giftfunding.osds.ui.address

import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse

interface AddressSearchClickListener {
    fun addressSearchClickable(addressName: AddressSearchResultDocumentResponse)
}