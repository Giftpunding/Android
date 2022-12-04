package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentHomeBinding
import com.giftfunding.osds.ui.home.adapter.BannerAdapter
import com.giftfunding.osds.util.infinityBanner

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_home
    private val labelList = ArrayList<String>()
    private var bannerSize : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init() {
        initBanner()
        initBannerCountTextView()
    }

    private fun initBanner() {
        val list = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_arrow_back,
            R.drawable.ic_arrow_top_scroll,
        )
        bannerSize = list.size

        val bannerAdapter = BannerAdapter(requireActivity())
        binding.vpHomeBanner.apply {
            adapter = bannerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        bannerAdapter.setBannerItems(list)
    }

    private fun initBannerCountTextView() {
        val bannerCountText = "$DEFAULT_PAGE / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    override fun initEvent() {
        binding.vpHomeBanner.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateBannerPosition(position.infinityBanner(bannerSize))
            }
        })
    }

    private fun updateBannerPosition(position: Int) {
        val bannerCountText = "${position+1} / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    override fun initObserverEvent() {
    }

    companion object{
        const val DEFAULT_PAGE = 1
    }
}