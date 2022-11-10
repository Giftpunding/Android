package com.giftfunding.osds.ui.funding

import android.os.Bundle
import com.bumptech.glide.Glide
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.repository.remote.datasource.dto.funding.FundingResultResponse
import com.giftfunding.osds.databinding.ActivityFundingResultInfoBinding
import com.giftfunding.osds.ui.enum.ToolbarType
import com.giftfunding.osds.ui.enum.VisibleState
import com.giftfunding.osds.util.addComma

class FundingResultActivity :
    BaseActivity<ActivityFundingResultInfoBinding>(ActivityFundingResultInfoBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setTitle(resources.getString(R.string.content_gift_history))
        setCloseButton(VisibleState.VISIBLE)

        val tempData: FundingResultResponse =
            FundingResultResponse(
                "img",
                "brand",
                "name",
                63000,
                "신용카드",
                13000,
                "홍길동",
                "축하해!"
            )

        binding.apply {
            Glide.with(this@FundingResultActivity)
                .load(tempData.img)
                .centerInside()
                .into(ivMerchandiseImg)

            tvTotalPrice.text = addComma(tempData.price)
            tvMerchandiseBrand.text = tempData.brand
            tvMerchandiseName.text = tempData.name
            tvPaymentPrice.text = addComma(tempData.paymentPrice)
            tvPaymentType.text = tempData.paymentType
            tvSenderName.text = tempData.sender
            tvMessage.text = tempData.message
        }
    }

    override fun initEvent() {

    }
}