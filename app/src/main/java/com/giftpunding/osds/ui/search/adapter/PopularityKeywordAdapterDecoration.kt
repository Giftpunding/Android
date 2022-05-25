package com.giftpunding.osds.ui.search.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PopularityKeywordAdapterDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if(parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1){
            outRect.bottom = dpToPx(parent.context, 14).toInt()
        }
    }

    private fun dpToPx(context: Context, space: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            space.toFloat(),
            context.resources.displayMetrics
        )
    }
}