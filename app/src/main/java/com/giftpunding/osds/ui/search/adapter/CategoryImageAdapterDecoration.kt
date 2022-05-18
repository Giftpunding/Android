package com.giftpunding.osds.ui.search.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryImageAdapterDecoration : RecyclerView.ItemDecoration() {
    private val space: Int = 8

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.setEmpty()

        val gridLayoutManagerParams: GridLayoutManager.LayoutParams =
            view.layoutParams as GridLayoutManager.LayoutParams

        val spanIndex: Int = gridLayoutManagerParams.spanIndex
        val pxSpace: Float = dpToPx(parent.context)
        val leftMargin: Int = getLeftMargin(pxSpace, spanIndex)
        val rightMargin: Int = getRightMargin(pxSpace, spanIndex)

        setMargin(outRect, pxSpace, leftMargin, rightMargin)
    }

    private fun getLeftMargin(space: Float, spanIndex: Int): Int {
        return spanIndex * (space / 3).toInt()
    }

    private fun getRightMargin(space: Float, spanIndex: Int): Int {
        return (space - (spanIndex + 1) * (space / 3)).toInt()
    }

    private fun setMargin(outRect: Rect, space: Float, leftMargin: Int, rightMargin: Int) {
        outRect.bottom = space.toInt()
        outRect.left = leftMargin
        outRect.right = rightMargin
    }

    private fun dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            space.toFloat(),
            context.resources.displayMetrics
        )
    }
}