package com.giftpunding.osds.ui.search.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PopularityKeywordAdapterDecoration : RecyclerView.ItemDecoration() {
    private val bottomSpacing: Int = 22

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dpToPx(parent.context).toInt()
    }

    private fun dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            bottomSpacing.toFloat(),
            context.resources.displayMetrics
        )
    }
}