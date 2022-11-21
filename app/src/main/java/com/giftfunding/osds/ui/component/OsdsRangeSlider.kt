package com.giftfunding.osds.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.giftfunding.osds.R
import com.google.android.material.slider.RangeSlider

class OsdsRangeSlider : RangeSlider {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private val to: Float = 0.0f
    private val from: Float = 100f
    private val size: Float = 20f
    private lateinit var tickCoordinates: ArrayList<Float>

    private var labelList = ArrayList<String>()

    private fun init(context: Context) {

        isTickVisible = false
        trackActiveTintList = ContextCompat.getColorStateList(context, R.color.tomato)!!
        trackInactiveTintList =
            ContextCompat.getColorStateList(context, R.color.hawkes_blue)!!
        setCustomThumbDrawable(R.drawable.tumb)
        stepSize = size
        val list = arrayListOf(
            to,
            from
        )
        values = list
    }

    override fun onDraw(canvas: Canvas) {
        //tick 좌표값 구하기
        maybeCalculateTicksCoordinates()
        super.onDraw(canvas)
        drawCustomTicks(canvas)

    }

    private fun maybeCalculateTicksCoordinates() {
        tickCoordinates = ArrayList()
        var tickCount = ((valueTo - valueFrom) / stepSize + 1).toInt()
        tickCount = Math.min(tickCount, trackWidth / (trackHeight * 2) + 1)
        if (tickCoordinates == null || tickCoordinates.size != tickCount * 2) {
            tickCoordinates = ArrayList(tickCount * 2)
        }

        val interval = trackWidth / (tickCount - 1).toFloat()
        var i = 1
        while (i < tickCount - 1) {
            tickCoordinates.add(trackSidePadding + i * interval)
            i += 1
        }
    }

    fun setLabels(labels: ArrayList<String>) {
        labelList = labels
    }

    private fun drawCustomTicks(canvas: Canvas) {
        val resources = context.resources
        val tickPaint = Paint()
        val baseLineYPosition = calculateTop()
        val density = resources.displayMetrics.density
        val tickHeight = density * 8
        val tickTopMargin = density * 3

        tickPaint.style = Paint.Style.STROKE
        tickPaint.strokeWidth = density * 1
        tickPaint.strokeCap = Paint.Cap.ROUND
        tickPaint.color = ResourcesCompat.getColor(resources, R.color.hawkes_blue, null)

        tickCoordinates.forEach {
            canvas.drawLine(
                it, baseLineYPosition.toFloat() + tickTopMargin,
                it, baseLineYPosition.toFloat() + tickHeight + tickTopMargin,
                tickPaint
            )
        }

        val labelTopMargin = density * 12
        val labelTextPaint = Paint()
        labelTextPaint.textSize = density * 10
        labelTextPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)

        if (labelList.size == 0) {
            tickCoordinates.forEachIndexed { index, _ ->
                labelList.add(index.toString())
            }
        }

        labelList.forEachIndexed { index, label ->
            val textDrawWidth = labelTextPaint.measureText(label)
            canvas.drawText(
                label,
                tickCoordinates[index] - textDrawWidth / 2,
                baseLineYPosition.toFloat() + tickHeight + tickTopMargin + labelTopMargin,
                labelTextPaint
            )
        }
    }

    private fun calculateTop(): Int =  context.resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_top)

}