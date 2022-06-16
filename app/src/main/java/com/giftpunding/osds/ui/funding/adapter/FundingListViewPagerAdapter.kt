package com.giftpunding.osds.ui.funding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.giftpunding.osds.ui.funding.FundingGiftMyListFragment
import com.giftpunding.osds.ui.funding.FundingListFragment

class FundingListViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                FundingGiftMyListFragment.getFragmentInstance()!!
            }
            else -> {
                FundingListFragment.getFragmentInstance()!!
            }
        }
    }
}