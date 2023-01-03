package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KakaoAddressResponse(
    @SerializedName("address_name")
    var addressName: String?,
    @SerializedName("region_1depth_name")
    val region1depthName: String?,
    @SerializedName("region_2depth_name")
    var region2depthName: String?,
    @SerializedName("region_3depth_name")
    var region3depthName: String?,
    @SerializedName("region_3depth_h_name")
    var region3depthHName: String?,
    @SerializedName("h_code")
    var hCode: String?,
    @SerializedName("b_code")
    var bCode: String?,
    @SerializedName("underground_yn")
    var undergroundYn: String?,
    @SerializedName("main_address_no")
    var mainAddressNo: String?,
    @SerializedName("sub_address_no")
    var subAddressNo: String?,
    @SerializedName("x")
    var x: String?,
    @SerializedName("y")
    var y: String?
) : Serializable
