package com.giftpunding.osds.ui.funding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.data.response.funding.FundingResponse
import com.giftpunding.osds.databinding.ActivityFundingListBinding
import com.giftpunding.osds.ui.funding.adapter.FundingAdapter

class FundingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFundingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFundingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initEvent()
    }

    private fun init() {
        /* 임시 더미데티어, 추후에 서버에서 받아올 예정 */
        val fList = mutableListOf<FundingResponse>()
        for (idx in 0 until 10) {
            fList.add(
                FundingResponse(
                    brand = "브랜드${idx}",
                    name = "상품이름",
                    img = "test",
                    nowFunding = 10000,
                    possibleFunding = 30000,
                    totalPrice = 40000
                )
            )
        }

        binding.apply {
            val fundingAdapter = FundingAdapter(this@FundingActivity)
            rvGiftList.layoutManager =
                LinearLayoutManager(this@FundingActivity, LinearLayoutManager.VERTICAL, false)
            rvGiftList.adapter = fundingAdapter
            fundingAdapter.addItemList(fList)
        }
    }

    private fun initEvent() {

    }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }
}