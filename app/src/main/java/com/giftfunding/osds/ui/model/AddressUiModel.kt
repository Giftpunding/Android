package com.giftfunding.osds.ui.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//프래그먼트에 객체를 넘길때 꼭 구현해줘야함
@Parcelize
data class AddressUiModel(
    val keywordAddress: String,
    val addressName: String,
    val addressType: String
): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.run{
            writeString(this@AddressUiModel.keywordAddress)
            writeString(this@AddressUiModel.addressName)
            writeString(this@AddressUiModel.addressType)
        }
    }
}
