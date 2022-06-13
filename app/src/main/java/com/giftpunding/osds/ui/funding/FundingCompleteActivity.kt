package com.giftpunding.osds.ui.funding

import android.os.Bundle
import android.view.View
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityFundingCompleteBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class FundingCompleteActivity :
    BaseActivity<ActivityFundingCompleteBinding>(ActivityFundingCompleteBinding::inflate),
    View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarType(ToolbarType.NORMAL)
        setCloseButton(VisibleState.VISIBLE)
        setTitle(resources.getString(R.string.title_gift_complete))

        init()
        initEvent()
    }

    override fun init() {

    }

    override fun initEvent() {
        binding.apply {
            btnConfirm.setOnClickListener(this@FundingCompleteActivity)
            btnGiftSendList.setOnClickListener(this@FundingCompleteActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnConfirm -> {

            }
            binding.btnGiftSendList -> {

            }
        }
    }
}