package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.FragmentHomeBinding
import com.giftfunding.osds.ui.home.adapter.BannerAdapter
import com.giftfunding.osds.ui.home.adapter.LuxuryGiftRecyclerViewAdapter
import com.giftfunding.osds.ui.home.adapter.MostSearchGiftRecyclerViewAdapter
import com.giftfunding.osds.ui.home.adapter.MostSelectGiftRecyclerViewAdapter
import com.giftfunding.osds.util.RecyclerViewDeco
import com.giftfunding.osds.util.infinityBanner

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_home
    private val labelList = ArrayList<String>()
    private var bannerSize: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init() {
        initBanner()
        initBannerCountTextView()
        initMostSearchGift()
        initMostSelectGift()
        initJoinForceGift()
    }

    // 배너 초기화
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

    // 배너 페이지 초기화
    private fun initBannerCountTextView() {
        val bannerCountText = "$DEFAULT_PAGE / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    // 가장 많이 찾는 선물 초기화
    private fun initMostSearchGift() {
        val list = listOf(
            R.drawable.ic_gifticon,
            R.drawable.ic_food,
            R.drawable.ic_beauty,
            R.drawable.ic_fashion,
            R.drawable.ic_living,
            R.drawable.ic_luxury,
            R.drawable.ic_digital,
            R.drawable.ic_all
        )

        val mostSearchGiftAdapter = MostSearchGiftRecyclerViewAdapter()
        binding.contentSearchGift.rvMainCategory.apply {
            adapter = mostSearchGiftAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 14f, 0f, 15f))
        }
        mostSearchGiftAdapter.setItems(list)
    }

    // 가장 많이 선택한 선물
    private fun initMostSelectGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..8) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 40000,
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val mostSelectGiftAdapter = MostSelectGiftRecyclerViewAdapter()
        binding.contentMostSelectGift.rvMostSelectGiftList.apply {
            adapter = mostSelectGiftAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 12f, 0f, 0f))
        }

        mostSelectGiftAdapter.setItems(list)
    }

    //힘을 모으면 살 수 있어 (명품)
    private fun initJoinForceGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..20) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 400000 + (idx*100000),
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val luxuryGiftAdapter = LuxuryGiftRecyclerViewAdapter()
        binding.contentJoinForceGift.rvLuxuryList.apply {
            adapter = luxuryGiftAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 0f, 0f, 12f))
        }

        luxuryGiftAdapter.setItems(list)
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
        val bannerCountText = "${position + 1} / $bannerSize"
        binding.tvBannerCount.text = bannerCountText
    }

    override fun initObserverEvent() {

    }

    companion object {
        const val DEFAULT_PAGE = 1
    }
}