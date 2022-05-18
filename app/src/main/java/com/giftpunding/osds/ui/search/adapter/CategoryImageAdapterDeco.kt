package com.giftpunding.osds.ui.search.adapter

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryImageAdapterDeco: RecyclerView.ItemDecoration() {
    private val space: Int = 8

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val gridLayoutManagerParams: GridLayoutManager.LayoutParams =
            view.layoutParams as GridLayoutManager.LayoutParams

        val px: Float = dpToPx(parent.context)
        val position: Int = parent.getChildAdapterPosition(view)
        val spanIndex: Int = gridLayoutManagerParams.spanIndex

        setMarginOfTopAndBottom(outRect, px, position)
        setMarginOfStartAndEnd(outRect, px, spanIndex)

    }

    private fun setMarginOfTopAndBottom(outRect: Rect, px: Float, position: Int) {
//        when (position){
//            in 0..2-> {
//                outRect.top = px.toInt()
//                outRect.bottom = px.toInt()
//            }
//            else -> outRect.bottom = px.toInt()
//        }
        outRect.bottom = px.toInt()
    }

    private fun setMarginOfStartAndEnd(outRect: Rect, px: Float, spanIndex: Int) {
        val span = Span.build(spanIndex)
        when(span){
            Span.LEFT -> {
                outRect.left = px.toInt()
                outRect.right = px.toInt()/2
            }

            Span.MIDDLE ->{
                outRect.left = px.toInt()/2
                outRect.right = px.toInt()/2
            }

            Span.RIGHT -> {
                outRect.left = px.toInt()/2
                outRect.right = px.toInt()
            }
        }
    }


    private fun dpToPx(context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            space.toFloat(),
            context.resources.displayMetrics
        )
    }
}