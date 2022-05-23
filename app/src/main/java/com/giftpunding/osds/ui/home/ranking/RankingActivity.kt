package com.giftpunding.osds.ui.home.ranking

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ActivityGiftRankingBinding
import com.giftpunding.osds.ui.home.adapter.MerchandiseAdapter

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGiftRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initEvent()
    }

    private fun init() {
        binding.apply {
            /* 임시 더미 데이터 */
            val list = mutableListOf<HomeMerchandiseResponse>()
            for (idx in 1..10) {
                list.add(
                    HomeMerchandiseResponse(
                        brand = "브랜드${idx}",
                        name = "상품 이름",
                        price = 10000,
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                    )
                )
            }
            val merchandiseAdapter = MerchandiseAdapter(this@RankingActivity)

            rvMerchandise.layoutManager =
                LinearLayoutManager(this@RankingActivity, LinearLayoutManager.VERTICAL, false)
            rvMerchandise.adapter = merchandiseAdapter

            merchandiseAdapter.addItemList(list)
        }
    }

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

                tvHomeGiftRankingAll.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
                tvHomeGiftRankingOneToTwo.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
                tvHomeGiftRankingFiveToNine.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
                tvHomeGiftRankingThreeToFour.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
                tvHomeGiftRankingOverTen.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
            }
            clickTextView.typeface =
                ResourcesCompat.getFont(this@RankingActivity, R.font.helveticaneue_bold)
            clickTextView.setTextColor(resources.getColor(R.color.black, null))
        }
    }
}