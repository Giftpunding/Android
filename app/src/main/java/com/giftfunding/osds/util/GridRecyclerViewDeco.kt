package com.giftfunding.osds.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridRecyclerViewDeco(var horizontalSpacing: Int, var verticalSpacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = verticalSpacing
            bottom = verticalSpacing
            left = horizontalSpacing
            right = horizontalSpacing
        }
    }
}