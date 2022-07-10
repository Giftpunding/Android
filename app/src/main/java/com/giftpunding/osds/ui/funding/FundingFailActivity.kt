package com.giftpunding.osds.ui.funding

import android.os.Bundle
import android.view.View
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityFundingFailureBinding
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class FundingFailActivity :
    BaseActivity<ActivityFundingFailureBinding>(ActivityFundingFailureBinding::inflate),
    View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setTitle(resources.getString(R.string.title_payment_failure))
        setCloseButton(VisibleState.VISIBLE)
        setBackButtonVisible(VisibleState.INVISIBLE)
    }

    override fun initEvent() {
        binding.apply {
            btnRetry.setOnClickListener(this@FundingFailActivity)
            btnConfirm.setOnClickListener(this@FundingFailActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnConfirm -> {

            }
            binding.btnRetry -> {

            }
        }
    }
}