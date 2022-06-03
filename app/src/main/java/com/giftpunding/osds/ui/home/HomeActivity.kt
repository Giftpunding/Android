package com.giftpunding.osds.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.luxuryGift.LuxuryGiftResponse
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.data.response.home.popualrGift.PopularGiftCategoryResponse
import com.giftpunding.osds.data.response.home.popualrGift.PopularGiftResponse
import com.giftpunding.osds.databinding.ActivityHomeBinding
import com.giftpunding.osds.ui.home.adpater.LuxuryAdapter
import com.giftpunding.osds.ui.home.adpater.RecommendAdapter
import com.giftpunding.osds.ui.merchandise.adapter.MerchandiseAdapter
import com.giftpunding.osds.ui.home.ranking.RankingActivity
import com.giftpunding.osds.ui.home.popular.adapter.PopularGiftCategoryAdapter
import com.giftpunding.osds.ui.home.popular.adapter.PopularGiftPagerAdapter
import kotlin.math.ceil

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initEvent()
    }

    /**
     * 액티비티 실행 시 초기화 작업,
     * 서버 작업 요구 됨
     */
    private fun init() {
        //서버 통신 작업 필요함, 임시로 어댑터 연결
        binding.apply {
            /* 임시 더미 데이터 */
            val mList = mutableListOf<MerchandiseResponse>()
            val lList = mutableListOf<LuxuryGiftResponse>()
            val sList = mutableListOf<PopularGiftResponse>()
            val cList = mutableListOf<PopularGiftCategoryResponse>()

            for (idx in 1..13) {
                mList.add(
                    MerchandiseResponse(
                        brand = "브랜드${idx}",
                        name = "상품 이름",
                        price = 10000,
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                    )
                )
            }
            for (idx in 1..13) {
                sList.add(
                    PopularGiftResponse(
                        brand = "브랜드${idx}",
                        name = "상품 이름",
                        price = 10000,
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                    )
                )
            }
            for (idx in 1..5) {
                lList.add(
                    LuxuryGiftResponse(
                        brand = "브랜드${idx}",
                        name = "상품 이름",
                        price = 10000,
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                    )
                )
            }
            for (idx in 1..15) {
                cList.add(
                    PopularGiftCategoryResponse(
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg",
                        category = "카테고리$idx",
                        check = false
                    )
                )
            }

            //카테고리 연결
            rvHomeSoughtAfterGiftCategory.apply {
                val soughtAfterAdapter = PopularGiftCategoryAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter =
                    soughtAfterAdapter
                soughtAfterAdapter.addItemList(cList)
            }

            //상품 리스트 연결
            rvHomeGiftMerchandise.apply {
                val merchandiseAdapter = MerchandiseAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                adapter = merchandiseAdapter
                merchandiseAdapter.addItemList(mList)
            }

            //명품 연결
            rvLuxuryList.apply {
                val luxuryAdapter = LuxuryAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = luxuryAdapter
                luxuryAdapter.addItemList(lList)
            }

            //추천 리스트 연결
            rvRecommendList.apply {
                /* 임시 더미 데이터 */
                val list = mutableListOf<MerchandiseResponse>()
                for (idx in 1..5) {
                    list.add(
                        MerchandiseResponse(
                            brand = "브랜드${idx}",
                            name = "상품 이름",
                            price = 10000,
                            img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                        )
                    )
                }
                val recommendAdapter = RecommendAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = recommendAdapter
                recommendAdapter.addItemList(list)
            }

            //많이 찾는 선물 페이지 크기
            val fragmentSize = ceil(mList.size.toDouble().div(4)).toInt()

            //많이 찾는 선물 뷰페이저 연결
            vpSoughtAfterGift.apply {
                adapter =
                    PopularGiftPagerAdapter(this@HomeActivity, fragmentSize, sList)

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tvSoughtAfterCategoryPagePrevious.text = position.plus(1).toString()
                    }
                })
            }
            tvSoughtAfterCategoryPageAfter.text = fragmentSize.toString()
        }
    }

    /**
     * 상황에 맞는 View 이벤트 등록,
     * 현재는 글자 폰트만 변경됩니다.
     */
    private fun initEvent() {
        binding.apply {
            tvHomeGiftAll.setOnClickListener(this@HomeActivity)
            tvHomeGiftOneToTwo.setOnClickListener(this@HomeActivity)
            tvHomeGiftThreeToFour.setOnClickListener(this@HomeActivity)
            tvHomeGiftFiveToNine.setOnClickListener(this@HomeActivity)
            tvHomeGiftOverTen.setOnClickListener(this@HomeActivity)
            lyMerchandiseMoreInfo.setOnClickListener(this@HomeActivity)
            btnSoughtAfterBack.setOnClickListener(this@HomeActivity)
            btnSoughtAfterAfter.setOnClickListener(this@HomeActivity)
        }
    }

    /**
     * 카테고리 클릭 시 발생하는 이벤트,
     * 현재는 글자 폰트만 변경됩니다.
     */
    private fun changeCategoryStyle(clickTextView: TextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.apply {
                tvHomeGiftAll.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftOneToTwo.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftThreeToFour.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftFiveToNine.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftOverTen.setTextColor(resources.getColor(R.color.mischka, null))

                tvHomeGiftAll.typeface =
                    ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
                tvHomeGiftOneToTwo.typeface =
                    ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
                tvHomeGiftThreeToFour.typeface =
                    ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
                tvHomeGiftFiveToNine.typeface =
                    ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
                tvHomeGiftOverTen.typeface =
                    ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
            }
            clickTextView.typeface =
                ResourcesCompat.getFont(this@HomeActivity, R.font.helveticaneue_bold)
            clickTextView.setTextColor(resources.getColor(R.color.black, null))
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.tvHomeGiftAll -> {
                changeCategoryStyle(binding.tvHomeGiftAll)
            }
            binding.tvHomeGiftOneToTwo -> {
                changeCategoryStyle(binding.tvHomeGiftOneToTwo)
            }
            binding.tvHomeGiftThreeToFour -> {
                changeCategoryStyle(binding.tvHomeGiftThreeToFour)
            }
            binding.tvHomeGiftFiveToNine -> {
                changeCategoryStyle(binding.tvHomeGiftFiveToNine)
            }
            binding.tvHomeGiftOverTen -> {
                changeCategoryStyle(binding.tvHomeGiftOverTen)
            }
            binding.lyMerchandiseMoreInfo -> {
                startActivity(Intent(this, RankingActivity::class.java))
            }
            //페이지 버튼 이벤트
            binding.btnSoughtAfterAfter -> {
                if (!binding.tvSoughtAfterCategoryPageAfter.text.equals(binding.vpSoughtAfterGift.currentItem + 1)) {
                    binding.btnSoughtAfterAfter.isEnabled = true
                    binding.vpSoughtAfterGift.currentItem =
                        binding.vpSoughtAfterGift.currentItem.plus(1)
                    binding.tvSoughtAfterCategoryPagePrevious.text =
                        binding.vpSoughtAfterGift.currentItem.plus(1).toString()
                } else {
                    binding.btnSoughtAfterAfter.isEnabled = false
                }
            }
            binding.btnSoughtAfterBack -> {
                if (!binding.tvSoughtAfterCategoryPagePrevious.text.equals(binding.vpSoughtAfterGift.currentItem + 1)) {
                    binding.btnSoughtAfterBack.isEnabled = true
                    binding.vpSoughtAfterGift.currentItem =
                        binding.vpSoughtAfterGift.currentItem.minus(1)
                    binding.tvSoughtAfterCategoryPagePrevious.text =
                        binding.vpSoughtAfterGift.currentItem.plus(1).toString()
                } else {
                    binding.btnSoughtAfterBack.isEnabled = false
                }
            }
        }
    }
}