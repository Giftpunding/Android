package com.giftfunding.osds.util


fun String.convertMonth() : String{
    val month = this
    val result = if (month < "10") {
        "-0$month"
    } else {
        "-$month"
    }
    return result
}

fun String.convertDay() : String{
    val day = this
    val result = if (day < "10") {
        "-0$day"
    } else {
        "-$day"
    }
    return result
}
