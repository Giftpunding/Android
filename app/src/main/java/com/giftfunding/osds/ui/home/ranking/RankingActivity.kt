package com.giftfunding.osds.ui.home.ranking

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftfunding.osds.databinding.ActivityGiftRankingBinding

class RankingActivity : BaseActivity<ActivityGiftRankingBinding>(ActivityGiftRankingBinding::inflate), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        binding.apply {
            /* 임시 더미 데이터 */
            val list = mutableListOf<MerchandiseResponse>()
            for (idx in 1..10) {
                list.add(
                    MerchandiseResponse(
                        brand = "브랜드${idx}",
                        name = "상품 이름",
                        price = 10000,
                        img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                    )
                )
            }
            val merchandiseAdapter = RankingAdapter(this@RankingActivity)

            rvMerchandise.layoutManager =
                LinearLayoutManager(this@RankingActivity, LinearLayoutManager.VERTICAL, false)
            rvMerchandise.adapter = merchandiseAdapter

            merchandiseAdapter.addItemList(list)
        }
    }

    override fun initEvent() {
        binding.apply {
            tvHomeGiftAll.setOnClickListener(this@RankingActivity)
            tvHomeGiftOneToTwo.setOnClickListener(this@RankingActivity)
            tvHomeGiftThreeToFour.setOnClickListener(this@RankingActivity)
            tvHomeGiftFiveToNine.setOnClickListener(this@RankingActivity)
            tvHomeGiftOverTen.setOnClickListener(this@RankingActivity)
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
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
                tvHomeGiftOneToTwo.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
                tvHomeGiftThreeToFour.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
                tvHomeGiftFiveToNine.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
                tvHomeGiftOverTen.typeface =
                    ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
            }
            clickTextView.typeface =
                ResourcesCompat.getFont(this@RankingActivity, R.font.helveticanene_bold)
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
        }
    }
}