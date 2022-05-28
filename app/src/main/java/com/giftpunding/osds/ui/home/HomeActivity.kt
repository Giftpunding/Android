package com.giftpunding.osds.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.luxury.LuxuryResponse
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.databinding.ActivityHomeBinding
import com.giftpunding.osds.ui.home.adpater.LuxuryAdapter
import com.giftpunding.osds.ui.home.adpater.RecommendAdapter
import com.giftpunding.osds.ui.home.merchandise.MerchandiseAdapter
import com.giftpunding.osds.ui.home.ranking.RankingActivity

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
            rvHomeGiftMerchandise.apply {
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
                val merchandiseAdapter = MerchandiseAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                adapter = merchandiseAdapter
                merchandiseAdapter.addItemList(list)
            }
            rvLuxuryList.apply {
                /* 임시 더미 데이터 */
                val list = mutableListOf<LuxuryResponse>()
                for (idx in 1..5) {
                    list.add(
                        LuxuryResponse(
                            brand = "브랜드${idx}",
                            name = "상품 이름",
                            price = 10000,
                            img = "http://www.selphone.co.kr/homepage/img/team/3.jpg"
                        )
                    )
                }
                val luxuryAdapter = LuxuryAdapter(this@HomeActivity)
                layoutManager =
                    LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = luxuryAdapter
                luxuryAdapter.addItemList(list)
            }
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
        }
    }
}