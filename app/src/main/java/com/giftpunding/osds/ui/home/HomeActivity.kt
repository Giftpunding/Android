package com.giftpunding.osds.ui.home

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initEvent()
    }

    private fun init() {

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