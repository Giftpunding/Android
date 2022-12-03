package com.giftfunding.osds.ui.home

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentHomeBinding
import com.giftfunding.osds.ui.home.adapter.BannerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_home
    private val labelList = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init(){
        initBanner()

    }

    @SuppressLint("Recycle")
    private fun initBanner() {
        val bannerAdapter = BannerAdapter()
        binding.vpHomeBanner.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        val bannerImages: TypedArray = resources.obtainTypedArray(R.array.bannerImage)
        bannerAdapter.setBannerItems(bannerImages)
    }

    override fun initEvent() {
        binding.vpHomeBanner.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d("TEST!!!!", "onPageScrollStateChanged")

            }
        })
    }

    override fun initObserverEvent() {
    }
}