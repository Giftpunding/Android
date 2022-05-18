package com.giftpunding.osds.ui.search.adapter

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration:
    RecyclerView.ItemDecoration() {

    private val spacing: Int = 8

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val space: Float = dpToPx(parent.context)
            Log.d("getItemOffsets", "$space")
            top = space.toInt()
            bottom = space.toInt()
            left = space.toInt()
            right = space.toInt()
        }
    }

    private fun dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            spacing.toFloat(),
            context.resources.displayMetrics
        )
    }
}