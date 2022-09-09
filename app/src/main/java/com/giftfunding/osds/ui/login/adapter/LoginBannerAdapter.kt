package com.giftfunding.osds.ui.login.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LoginBannerAdapter(private val imgList : List<Int>, fm : FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount() = imgList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = LoginBannerFragment.getFragmentInstance()
        fragment?.arguments = Bundle().apply {
            this.putInt("img", imgList[position])
        }
        return fragment!!
    }
}