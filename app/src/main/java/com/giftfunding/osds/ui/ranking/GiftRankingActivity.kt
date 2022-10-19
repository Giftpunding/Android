package com.giftfunding.osds.ui.ranking

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.home.item.ItemResponse
import com.giftfunding.osds.databinding.ActivityRankingBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState
import com.giftfunding.osds.ui.ranking.adapter.GiftRankingAdapter

class GiftRankingActivity : BaseActivity<ActivityRankingBinding>(ActivityRankingBinding::inflate) {

    private val viewModel: GiftRankingViewModel by viewModels()
    private lateinit var giftRankingAdapter: GiftRankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()
        initRecyclerView()
        initGiftRankingViewModel()
        //선물 랭킹 아이템 가져오기
        getGiftList()
    }

    override fun init() {
        initToolbar()
        initTopScrollEvent()
    }

    private fun initToolbar() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButton(BackButton.BACK)
        setTitle(resources.getString(R.string.title_gift_ranking))
        setBadge(VisibleState.VISIBLE)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTopScrollEvent() {
        binding.ssContainer.setOnTouchListener { view, _ ->
            if (view?.canScrollVertically(top) == true) {
                binding.btnTopScroll.visibility = View.VISIBLE
            } else {
                binding.btnTopScroll.visibility = View.GONE
            }
            false
        }

        binding.btnTopScroll.setOnClickListener {
            binding.ssContainer.fullScroll(ScrollView.FOCUS_UP)
            it.visibility = View.GONE
        }
    }

    override fun initEvent() {

    }

    private fun initRecyclerView() {
        giftRankingAdapter = GiftRankingAdapter()
        binding.rvGiftList.apply {
            layoutManager =
                LinearLayoutManager(this@GiftRankingActivity, LinearLayoutManager.VERTICAL, false)
            adapter = giftRankingAdapter
        }
    }

    private fun initGiftRankingViewModel() {
        viewModel.giftResponse.observe(this){ gifts ->
            updateView(gifts.size)
            if(gifts.isNotEmpty()){
                adapterAddItems(gifts)
            }
        }
    }

    private fun getGiftList() {
        viewModel.getGiftList()
    }

    private fun updateView(size: Int) {
        when(size){
            0 -> {
                noResultView()
            }
            in 1 .. 4 ->{
                rangeOneToFourView()
            }else -> {
            fiveOrMoreView()
        }
        }
    }

    //선물 랭킹 결과 없을 때
    private fun noResultView() {
        binding.rvGiftList.visibility = View.GONE
        binding.tvBackScreen.visibility = View.GONE
        binding.contentNoResult.root.visibility = View.VISIBLE
        binding.contentNoResult.tvNoAddressResult.text = "testtest"
    }

    // 선물 랭킹 결과 1 ~ 4개 존재
    private fun rangeOneToFourView() {
        binding.tvBackScreen.visibility = View.VISIBLE
        binding.tvBackScreen.text = "메뉴명"
        binding.contentNoResult.root.visibility = View.GONE
    }

    //선물 랭킹 결과 5개 이상
    private fun fiveOrMoreView() {
        binding.rvGiftList.visibility = View.VISIBLE
        binding.tvBackScreen.visibility = View.GONE
        binding.contentNoResult.root.visibility = View.GONE
    }

    private fun adapterAddItems(gifts: List<ItemResponse>) {
        giftRankingAdapter.addItems(gifts)
    }

    companion object{
        private const val top = -1
    }
}