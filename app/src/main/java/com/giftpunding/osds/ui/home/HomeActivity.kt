package com.giftpunding.osds.ui.home

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
<<<<<<< HEAD
import com.giftpunding.osds.data.response.home.HomeGiftBoxResponse
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ActivityHomeBinding
import com.giftpunding.osds.ui.home.adapter.HomeGiftBoxAdapter
import com.giftpunding.osds.ui.home.adapter.HomeMerchandiseAdapter
=======
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
>>>>>>> Feature/#1-홈화면

class HomeActivity : AppCompatActivity() {

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
<<<<<<< HEAD
            rvHomeGiftBoxList.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            rvHomeGiftBoxList.adapter =
                HomeGiftBoxAdapter(this@HomeActivity, listOf(HomeGiftBoxResponse(test = "123123")))
=======
            /* 임시 더미 데이터 */
            val mList = mutableListOf<MerchandiseResponse>()
            val lList = mutableListOf<LuxuryGiftResponse>()
            val sList = mutableListOf<PopularGiftResponse>()
            val cList = mutableListOf<PopularGiftCategoryResponse>()
>>>>>>> Feature/#1-홈화면

            rvHomeGiftMerchandise.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            rvHomeGiftMerchandise.adapter = HomeMerchandiseAdapter(
                this@HomeActivity, listOf(
                    HomeMerchandiseResponse(test = "123123123123123")
                )
<<<<<<< HEAD
            )
=======
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
>>>>>>> Feature/#1-홈화면
        }
    }

    /**
     * 상황에 맞는 View 이벤트 등록,
     * 현재는 글자 폰트만 변경됩니다.
     */
    private fun initEvent() {
        binding.apply {
            tvHomeGiftRankingAll.setOnClickListener {
                changeCategoryStyle(it as TextView)
            }
            tvHomeGiftRankingOneToTwo.setOnClickListener {
                changeCategoryStyle(it as TextView)
            }
            tvHomeGiftRankingThreeToFour.setOnClickListener {
                changeCategoryStyle(it as TextView)
            }
            tvHomeGiftRankingFiveToNine.setOnClickListener {
                changeCategoryStyle(it as TextView)
            }
            tvHomeGiftRankingOverTen.setOnClickListener {
                changeCategoryStyle(it as TextView)
            }
        }
    }

    /**
     * 카테고리 클릭 시 발생하는 이벤트,
     * 현재는 글자 폰트만 변경됩니다.
     */
    private fun changeCategoryStyle(clickTextView: TextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.apply {
                tvHomeGiftRankingAll.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftRankingOneToTwo.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftRankingFiveToNine.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftRankingThreeToFour.setTextColor(resources.getColor(R.color.mischka, null))
                tvHomeGiftRankingOverTen.setTextColor(resources.getColor(R.color.mischka, null))

                tvHomeGiftRankingAll.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
                tvHomeGiftRankingOneToTwo.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
                tvHomeGiftRankingFiveToNine.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
                tvHomeGiftRankingThreeToFour.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
                tvHomeGiftRankingOverTen.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
            }
            clickTextView.typeface = ResourcesCompat.getFont(this@HomeActivity,R.font.helvetica_neue_bold)
            clickTextView.setTextColor(resources.getColor(R.color.black, null))
        }
    }
}