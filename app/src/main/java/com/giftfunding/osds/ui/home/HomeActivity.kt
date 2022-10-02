package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityHomeBinding
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.ui.home.adapter.HomeBannerAdapter
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
        setToolbarType(ToolbarType.GIFT)
    }

    private fun initSearchInputPopUp() {

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