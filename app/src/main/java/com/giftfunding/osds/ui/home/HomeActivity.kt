package com.giftfunding.osds.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.home.luxuryGift.LuxuryGiftResponse
import com.giftfunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftfunding.osds.data.response.home.popualrGift.PopularGiftCategoryResponse
import com.giftfunding.osds.data.response.home.popualrGift.PopularGiftResponse
import com.giftfunding.osds.databinding.ActivityHomeBinding
import com.giftfunding.osds.ui.home.adpater.LuxuryAdapter
import com.giftfunding.osds.ui.merchandise.adapter.MerchandiseAdapter
import com.giftfunding.osds.ui.home.ranking.RankingActivity
import com.giftfunding.osds.ui.home.popular.adapter.PopularGiftCategoryAdapter
import com.giftfunding.osds.ui.home.popular.adapter.PopularGiftPagerAdapter
import com.giftfunding.osds.util.setTextStyleBold
import kotlin.math.ceil

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        //서버 통신 작업 필요함, 임시로 어댑터 연결
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
        initExpensiveGiftList(lList)
        initPopularGiftList(mList)
        initMostSearchedGiftList(cList, mList, sList)
        initRadioButtonEvent()
    }

    override fun initEvent() {

    }

    private fun initPopularGiftList(mList: MutableList<MerchandiseResponse>) {
        //상품 리스트 연결
        binding.contentPopularGiftList.apply {
            val merchandiseAdapter = MerchandiseAdapter(this@HomeActivity)
            rvHomeGiftMerchandise.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            rvHomeGiftMerchandise.adapter = merchandiseAdapter
            merchandiseAdapter.addItemList(mList)

            lyMerchandiseMoreInfo.setOnClickListener {
                startActivity(Intent(this@HomeActivity, RankingActivity::class.java))
            }
        }
    }

    private fun initMostSearchedGiftList(
        cList: MutableList<PopularGiftCategoryResponse>,
        mList: MutableList<MerchandiseResponse>,
        sList: MutableList<PopularGiftResponse>
    ) {
        //카테고리 연결
        binding.contentMostSearchedGiftList.apply {
            val searchedGiftListAdapter = PopularGiftCategoryAdapter(this@HomeActivity)
            rvHomeMostSearchedGiftCategory.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rvHomeMostSearchedGiftCategory.adapter =
                searchedGiftListAdapter
            searchedGiftListAdapter.addItemList(cList)

            //많이 찾는 선물 페이지 크기
            val fragmentSize = ceil(mList.size.toDouble().div(4)).toInt()

            //많이 찾는 선물 뷰페이저 연결
            vpSoughtAfterGift.apply {
                adapter =
                    PopularGiftPagerAdapter(this@HomeActivity, fragmentSize, sList)

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        binding.contentMostSearchedGiftList.tvSoughtAfterCategoryPagePrevious.text =
                            position.plus(1).toString()
                    }
                })
            }
            tvSoughtAfterCategoryPageAfter.text =
                fragmentSize.toString()

            btnSoughtAfterAfter.setOnClickListener {
                if (!tvSoughtAfterCategoryPageAfter.text.equals(vpSoughtAfterGift.currentItem + 1)) {
                    btnSoughtAfterAfter.isEnabled = true
                    vpSoughtAfterGift.currentItem =
                        vpSoughtAfterGift.currentItem.plus(1)
                    tvSoughtAfterCategoryPagePrevious.text =
                        vpSoughtAfterGift.currentItem.plus(1).toString()
                } else {
                    btnSoughtAfterAfter.isEnabled = false
                }
            }

            btnSoughtAfterBack.setOnClickListener {
                if (!tvSoughtAfterCategoryPagePrevious.text.equals(vpSoughtAfterGift.currentItem + 1)) {
                    btnSoughtAfterBack.isEnabled = true
                    vpSoughtAfterGift.currentItem =
                        vpSoughtAfterGift.currentItem.minus(1)
                    tvSoughtAfterCategoryPagePrevious.text =
                        vpSoughtAfterGift.currentItem.plus(1).toString()
                } else {
                    btnSoughtAfterBack.isEnabled = false
                }
            }
        }
    }

    private fun initExpensiveGiftList(lList: MutableList<LuxuryGiftResponse>) {
        //명품 연결
        binding.contentExpensiveGiftList.rvLuxuryList.apply {
            val luxuryAdapter = LuxuryAdapter(this@HomeActivity)
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = luxuryAdapter
            luxuryAdapter.addItemList(lList)
        }
    }

    //서버 연동 작업 전임, 단순히 텍스트 변화만 표시
    private fun initRadioButtonEvent(){
        //많이 받고 싶어 하는 선물
        binding.contentPopularGiftList.rBtnAll.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("test",TAG)
        }
    }
    companion object{
        private const val TAG = "HomeActivity..."
    }
}