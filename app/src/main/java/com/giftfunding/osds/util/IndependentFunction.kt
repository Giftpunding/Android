package com.giftfunding.osds.util

import android.content.Context
import android.util.TypedValue

fun dpToPx(context: Context, dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
        .toInt()

fun pxToDp(context: Context, px: Float): Int {
    var density = context.resources.displayMetrics.density
    when (density) {
        1.0f -> {
            density *= 4.0f
        }
        1.5f -> {
            density *= (8f / 3f)
        }
        2.0f -> {
            density *= 2.0f
        }
        3.0f -> {
            density *= (8f / 5f)
        }
        else -> {
            density *= 8f / 6f
        }
    }
    return (px / density).toInt()
}

fun addComma(number: Int): String = if (number >= 0) {
    "%,d".format(number)
} else {
    "- "
}
