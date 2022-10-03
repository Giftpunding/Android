package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.home.item.ItemCategoryResponse
import com.giftfunding.osds.data.response.home.item.ItemResponse
import com.giftfunding.osds.databinding.ActivityHomeBinding
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.ui.home.adapter.HomeBannerAdapter
import com.giftfunding.osds.ui.home.adapter.HomeMainCategoryAdapter
import com.giftfunding.osds.ui.home.adapter.HomeMostSelectedListAdapter
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private var bannerPosition = 0
    private val autoBannerSlider by lazy { initAutoBanner() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()
    }

    override fun init() {
        initToolbar()
        initSearchInputPopUp()
        initBanner()
        initMainCategoryList()
        initMostSelectedGiftList()
    }

    override fun initEvent() {

    }

    private fun initAutoBanner(): Job {
        return lifecycleScope.launchWhenResumed {
            delay(4000)
            binding.vp2HomeBanner.currentItem = ++bannerPosition
        }
    }

    private fun initToolbar() {
        setToolbarType(ToolbarType.GIFT, R.id.ly_home_main)
    }

    private fun initSearchInputPopUp() {
        binding.editSearchGift.showAlignTop(makeBalloon())
    }

    //최상위 함수로 뺄 예정
    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(this)
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(resources.getString(R.string.content_login_tutorial))
            .setTextColorResource(R.color.hawkes_blue)
            .setTextTypeface(ResourcesCompat.getFont(this, R.font.pretendard_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setIconDrawableResource(R.drawable.ic_launcher_background)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(8)
            .setPaddingLeft(13)
            .setPaddingRight(13)
            .setPaddingBottom(8)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.bright_grey)
            .setDismissWhenClicked(false)
            .setDismissWhenOverlayClicked(false)
            .setDismissWhenTouchOutside(false)

        return popUpMessage.build()
    }

    private fun initBanner() {
        //test input data
        val list = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

        binding.vp2HomeBanner.apply {
            adapter = HomeBannerAdapter(this@HomeActivity, list)
            setAutoBanner(list.size)
        }
    }

    private fun initMainCategoryList(){
        //test input data
        val list = mutableListOf<ItemCategoryResponse>()
        for(idx in 1..8){
            list.add(ItemCategoryResponse(R.drawable.ic_launcher_background, "카테고리$idx"))
        }

        binding.rcvMainCategory.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2, GridLayoutManager.HORIZONTAL, false)
            adapter = HomeMainCategoryAdapter(this@HomeActivity, list.toList())
        }
    }

    private fun initMostSelectedGiftList(){
        //test input data
        val list = mutableListOf<ItemResponse>()
        for(idx in 1..8){
            list.add(ItemResponse(idx = idx, name = "상품$idx", price = 40000, brand = "브랜드$idx", img = R.drawable.ic_launcher_background))
        }

        binding.rcvAnotherPeopleSelectedGiftList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            adapter = HomeMostSelectedListAdapter(this@HomeActivity, list.toList())
        }
    }

    private fun ViewPager2.setAutoBanner(bannerSize: Int) {

        this.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        if (!autoBannerSlider.isActive) autoBannerSlider.start()
                    }
                    ViewPager2.SCROLL_STATE_DRAGGING -> autoBannerSlider.cancel()
                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvBannerCount.text = "${(position % bannerSize) + 1} / $bannerSize"
            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        autoBannerSlider.start()
    }

    override fun onPause() {
        super.onPause()
        autoBannerSlider.cancel()
    }

    companion object {
        private const val TAG = "HomeActivity..."
    }
}