package com.giftfunding.osds.util

/**
 * this = position
 * size = bannerSize
 */
fun Int.infinityBanner(size: Int): Int {
    var index = this % size
    if (index >= size) {
        index = 0
    }
    return index
}