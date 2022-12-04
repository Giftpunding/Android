package com.giftfunding.osds.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.giftfunding.osds.ui.home.HomeBannerFragment
import com.giftfunding.osds.util.infinityBanner

class BannerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private var bannerItems = mutableListOf<Int>()

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val index = position.infinityBanner(bannerItems.size)
        return HomeBannerFragment.newInstance(bannerItems[index])
    }

    fun setBannerItems(bannerImages: List<Int>) {
        this.bannerItems = bannerImages as MutableList<Int>
    }
}