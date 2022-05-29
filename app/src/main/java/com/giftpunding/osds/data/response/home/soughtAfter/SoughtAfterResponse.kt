package com.giftpunding.osds.data.response.home.soughtAfter

import android.os.Parcel
import android.os.Parcelable

data class SoughtAfterResponse(
    val brand: String?,
    val name: String?,
    val price: Int,
    val img: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SoughtAfterResponse> {
        override fun createFromParcel(parcel: Parcel): SoughtAfterResponse {
            return SoughtAfterResponse(parcel)
        }

        override fun newArray(size: Int): Array<SoughtAfterResponse?> {
            return arrayOfNulls(size)
        }
    }
}