package com.giftpunding.osds.callback

import com.giftpunding.osds.data.response.address.AddressSearchResultResponse

interface AddressSearchResultCallback {
    fun onSuccessAddressSearchResult(response : AddressSearchResultResponse)
    fun onFailureAddressSearchResult(error : String)
}