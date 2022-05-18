package com.giftpunding.osds.ui.merchandise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.giftpunding.osds.databinding.ActivityMerchandiseInfoBinding
import com.giftpunding.osds.ui.merchandise.adapter.MerchandiseViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MerchandiseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMerchandiseInfoBinding
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val tabLayoutText = arrayOf("상품 설명", "상세 정보")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMerchandiseInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initEvent()
    }

    private fun init() {
        //프래그먼트 각자를 인스턴스 1번만 사용하게 하는 방법이 존재함.
        //이 방법을 사용해서 문제가 없을까?
        fragmentList.add(MerchandiseInfoFragment.getFragmentInstance()!!)
        fragmentList.add(MerchandiseDetailInfoFragment.getFragmentInstance()!!)

        binding.vpMerchandiseInfo.adapter = MerchandiseViewPagerAdapter(this, fragmentList)
        TabLayoutMediator(binding.lyMerchandiseTab, binding.vpMerchandiseInfo) { tab, position ->
            tab.text = tabLayoutText[position]
        }.attach()
    }

    private fun initEvent() {
        binding.lyMerchandiseTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabPosition = tab?.position
                binding.vpMerchandiseInfo.currentItem = tabPosition!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}