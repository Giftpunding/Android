package com.giftfunding.osds.ui.ranking

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.FragmentGiftRankingBinding
import com.giftfunding.osds.ui.address.AddressDetailFragmentArgs
import com.giftfunding.osds.ui.home.adapter.MostSelectGiftRecyclerViewAdapter
import com.giftfunding.osds.util.RecyclerViewDeco

class GiftRankingFragment : BaseFragment<FragmentGiftRankingBinding>() {
    private val args: GiftRankingFragmentArgs by navArgs()
    override fun layoutResId(): Int = R.layout.fragment_gift_ranking

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.rgAllCategory.check(args.type)
        initMostSelectGift()
    }

    override fun initEvent() {
        //TODO 체크 될때 서버 통신
        binding.rgAllCategory.setOnCheckedChangeListener { group, checkedId ->

        }
    }

    override fun initObserverEvent() {

    }

    // 가장 많이 선택한 선물
    private fun initMostSelectGift() {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..8) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 40000,
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }

        val mostSelectGiftAdapter = MostSelectGiftRecyclerViewAdapter()
        binding.rvMostSelectGiftList.apply {
            adapter = mostSelectGiftAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f, 12f, 0f, 0f))
        }

        mostSelectGiftAdapter.setItems(list)
        mostSelectGiftAdapter.setFullSize(true)
    }

    companion object{
        private const val TAG = "GiftRankingFragment"
    }
}