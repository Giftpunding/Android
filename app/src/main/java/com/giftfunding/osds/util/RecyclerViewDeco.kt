package com.giftfunding.osds.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDeco(
    private val top: Float,
    private val bottom: Float,
    private val left: Float,
    private val right: Float
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = dpToPx(parent.context, top)
        outRect.bottom = dpToPx(parent.context, bottom)
        outRect.left = dpToPx(parent.context, left)
        outRect.right = dpToPx(parent.context, right)
    }
}