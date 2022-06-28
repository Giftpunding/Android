package com.giftpunding.osds.ui.funding

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityFundingMyListInfoBinding
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState
import com.giftpunding.osds.ui.funding.adapter.FundingListViewPagerAdapter

class FundingMyListInfoActivity :
    BaseActivity<ActivityFundingMyListInfoBinding>(ActivityFundingMyListInfoBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.INVISIBLE)
        setCloseButton(VisibleState.INVISIBLE)
        setTitle(resources.getString(R.string.title_funding_list))

        binding.apply {
            vpFundingList.adapter = FundingListViewPagerAdapter(this@FundingMyListInfoActivity)
            vpFundingList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    navFundingList.menu.getItem(position).isChecked = true
                }
            })
            navFundingList.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item_funding_list -> {
                        vpFundingList.currentItem = 1
                    }
                    R.id.item_gift_list -> {
                        vpFundingList.currentItem = 0
                    }
                }
                true
            }
        }
    }

    override fun initEvent() {

    }
}