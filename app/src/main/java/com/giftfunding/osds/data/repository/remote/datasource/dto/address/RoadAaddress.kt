package com.giftfunding.osds.data.repository.remote.datasource.dto.address

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoadAaddress(
    @SerializedName("address_name")
    var addressName: String?,
    @SerializedName("region_1depth_name")
    val region1depthName: String?,
    @SerializedName("region_2depth_name")
    var region2depthName: String?,
    @SerializedName("region_3depth_name")
    var region3depthName: String?,
    @SerializedName("road_name")
    var roadName: String?,
    @SerializedName("underground_yn")
    var undergroundYn: String?,
    @SerializedName("main_building_no")
    var mainBuildingNo: String?,
    @SerializedName("sub_building_no")
    var subBuildingNo: String?,
    @SerializedName("building_name")
    var buildingName: String?,
    @SerializedName("zone_no")
    var zoneNo: String?,
    @SerializedName("x")
    var x: String?,
    @SerializedName("y")
    var y: String?
) : Serializable