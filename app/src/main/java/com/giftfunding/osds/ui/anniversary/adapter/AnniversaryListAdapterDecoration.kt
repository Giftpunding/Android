package com.giftfunding.osds.ui.anniversary.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.util.dpToPx

class AnniversaryListAdapterDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val margin28dp = dpToPx(parent.context, 28F)
        val margin6dp = dpToPx(parent.context, 6F)

        when (parent.getChildAdapterPosition(view)) {
            0 -> outRect.left = margin28dp
            (state.itemCount - 1) -> {
                outRect.right = margin28dp
                outRect.left = margin6dp
            }
            else -> {
                outRect.left = margin6dp
            }
        }
    }
}