package com.giftpunding.osds.ui.home

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.HomeGiftBoxResponse
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ActivityHomeBinding
import com.giftpunding.osds.ui.home.adapter.HomeGiftBoxAdapter
import com.giftpunding.osds.ui.home.adapter.HomeMerchandiseAdapter

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
            rvHomeGiftBoxList.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            rvHomeGiftBoxList.adapter =
                HomeGiftBoxAdapter(this@HomeActivity, listOf(HomeGiftBoxResponse(test = "123123")))

            rvHomeGiftMerchandise.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            rvHomeGiftMerchandise.adapter = HomeMerchandiseAdapter(
                this@HomeActivity, listOf(
                    HomeMerchandiseResponse(test = "123123123123123")
                )
            )
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

                tvHomeGiftRankingAll.typeface = Typeface.DEFAULT
                tvHomeGiftRankingOneToTwo.typeface = Typeface.DEFAULT
                tvHomeGiftRankingFiveToNine.typeface = Typeface.DEFAULT
                tvHomeGiftRankingThreeToFour.typeface = Typeface.DEFAULT
                tvHomeGiftRankingOverTen.typeface = Typeface.DEFAULT
            }
            clickTextView.typeface = Typeface.DEFAULT_BOLD
            clickTextView.setTextColor(resources.getColor(R.color.black, null))
        }
    }
}