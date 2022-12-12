package com.giftfunding.osds.ui.address.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//프래그먼트에 객체를 넘길때 꼭 구현해줘야함
@Parcelize
data class AddressUiModel(
    val keywordAddress: String,
    val addressName: String,
    val addressType: String
): Parcelable