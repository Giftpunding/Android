package com.giftfunding.osds.data.response.home.popualrGift

import android.os.Parcel
import android.os.Parcelable

data class PopularGiftResponse(
    var brand: String?,
    var name: String?,
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

    companion object CREATOR : Parcelable.Creator<PopularGiftResponse> {
        override fun createFromParcel(parcel: Parcel): PopularGiftResponse {
            return PopularGiftResponse(parcel)
        }

        override fun newArray(size: Int): Array<PopularGiftResponse?> {
            return arrayOfNulls(size)
        }
    }
}