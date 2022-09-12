package com.giftfunding.osds.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.load.ResourceEncoder
import com.giftfunding.osds.R
import com.giftfunding.osds.util.setTextStyleBold

class ConstraintRadioGroup @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintHelper(context, attributeSet, defStyleAttr) {
    private lateinit var radioButtonGroup: Array<CompoundButton?>
    private var mProtectFromCheckedChange = false
    private var mCheckedId = -1


    override fun updatePreLayout(container: ConstraintLayout?) {
        super.updatePreLayout(container)
        radioButtonGroup = Array(mIds.size) { null }
        var count = 0
        if (container != null)
            for (mId in mIds) {
                val mView: CompoundButton? = container.findViewById(mId)
                radioButtonGroup[count++] = mView
                mView?.setOnCheckedChangeListener(checkedStateTracker)
            }
    }

    private val checkedStateTracker: CompoundButton.OnCheckedChangeListener =
        object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if (mProtectFromCheckedChange) {
                    return
                }
                mProtectFromCheckedChange = true
                clearCheckView()
                mProtectFromCheckedChange = false
                mCheckedId = buttonView.id
                buttonView.isChecked = isChecked

                Log.d("Radio", buttonView.text.toString())

                buttonView.typeface = ResourcesCompat.getFont(context, R.font.pretendard_medium)
                buttonView.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.midnight_express,
                        null
                    )
                )
            }
        }

    private fun clearCheckView() {
        for (compoundButton in radioButtonGroup) {
            compoundButton?.isChecked = false
        }
    }
}