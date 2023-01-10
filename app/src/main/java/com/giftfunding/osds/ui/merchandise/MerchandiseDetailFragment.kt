package com.giftfunding.osds.ui.merchandise

import android.os.Bundle
import android.view.View
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.FragmentMerchandiseDetailBinding

class MerchandiseDetailFragment : BaseFragment<FragmentMerchandiseDetailBinding>() {

    override fun layoutResId(): Int = R.layout.fragment_merchandise_detail
    private val tempList = addTempList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(){

    }

    private fun initMerchandiseImgSlider(){
        val tempList = listOf<Int>(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)

    }

    override fun initEvent() {

    }

    override fun initObserverEvent() {

    }

    private fun addTempList() : List<ItemResponse> {
        val list = mutableListOf<ItemResponse>()
        for (idx in 1..20) {
            list.add(
                ItemResponse(
                    idx = idx,
                    name = "상품$idx",
                    price = 400000 + (idx * 100000),
                    brand = "브랜드$idx",
                    img = R.drawable.ic_launcher_background
                )
            )
        }
        return list
    }
}