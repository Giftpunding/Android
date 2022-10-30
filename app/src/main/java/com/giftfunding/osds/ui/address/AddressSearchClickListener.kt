package com.giftfunding.osds.ui.address

import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse

//삭제예정
interface AddressSearchClickListener {
    fun addressSearchClickable(addressName: AddressSearchResultDocumentResponse)
}