package com.giftfunding.osds.util.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.giftfunding.osds.R
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.RangeSlider

class LabeledRangeSlider: RangeSlider {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    val viewTrackWidth: Int = trackWidth
    val viewTrackHeight: Int = trackHeight
    val viewValueTo: Float = valueTo
    val viewValueFrom: Float = valueFrom
    val viewValueStepSize: Float = stepSize
    var labelList = ArrayList<String>()
    lateinit var viewTickCoordinates: ArrayList<Float>

    private fun maybeCalculateTicksCoordinates() {
        viewTickCoordinates = ArrayList<Float>()

        var tickCount = ((valueTo - valueFrom) / stepSize + 1).toInt()
        // Limit the tickCount if they will be too dense.
        tickCount = Math.min(tickCount, trackWidth / (trackHeight * 2) + 1)

        val interval = trackWidth / (tickCount - 1).toFloat()
        var i = 1
        while (i < tickCount - 1) {
            viewTickCoordinates.add(trackSidePadding + i * interval)
            i += 1
        }
    }

    override fun onDraw(canvas: Canvas) {
        maybeCalculateTicksCoordinates()
        drawCustomTicks(canvas)
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec + (resources.displayMetrics.density * 50).toInt())
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

        viewTickCoordinates.forEach {
            canvas.drawLine(
                it, baseLineYPosition.toFloat() + tickTopMargin,
                it, baseLineYPosition.toFloat() + tickHeight + tickTopMargin,
                tickPaint)
        }

        val labelTopMargin = density * 12
        val labelTextPaint = Paint()
//        labelTextPaint.typeface = Typeface.createFromAsset(resources.assets, "font/pretendard_regular.otf")
        labelTextPaint.textSize = density * 12
        labelTextPaint.color = ResourcesCompat.getColor(resources, R.color.hawkes_blue, null)

        if(labelList.size == 0){
            viewTickCoordinates.forEachIndexed{ index, _ ->
                labelList.add(index.toString())
            }
        }

        labelList.forEachIndexed { index, label ->
            val textDrawWidth = labelTextPaint.measureText(label)
            canvas.drawText(label,
                viewTickCoordinates[index] - textDrawWidth/2,
                baseLineYPosition.toFloat() + tickHeight + tickTopMargin + labelTopMargin,
                labelTextPaint)
        }

    }
    fun setLabels(labels: ArrayList<String>){
        labelList = labels
    }

    private fun calculateTop(): Int {
        val resources = context.resources
        val trackTop = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_top)
        return trackTop
    }
}