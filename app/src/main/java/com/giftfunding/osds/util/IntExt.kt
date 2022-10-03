package com.giftfunding.osds.util

import android.content.Context
import android.util.TypedValue

fun Int.dpToPixel(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics
).toInt()