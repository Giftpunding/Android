package com.giftfunding.osds.ui.funding

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityFundingCompleteBinding
import com.giftfunding.osds.ui.enum.ToolbarType
import com.giftfunding.osds.ui.enum.VisibleState

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
                startActivity(Intent(this, FundingMyListInfoActivity::class.java))
            }
        }
    }
}