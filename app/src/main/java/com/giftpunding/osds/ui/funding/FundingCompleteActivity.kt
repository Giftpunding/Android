package com.giftpunding.osds.ui.funding

import android.os.Bundle
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseViewBindingActivity
import com.giftpunding.osds.databinding.ActivityFundingCompleteBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class FundingCompleteActivity:BaseViewBindingActivity<ActivityFundingCompleteBinding>(ActivityFundingCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarType(ToolbarType.NORMAL)
        setBackButton(BackButton.BACK)
        setCloseButton(VisibleState.VISIBLE)
        setTitle(resources.getString(R.string.title_gift_complete))

    }
}