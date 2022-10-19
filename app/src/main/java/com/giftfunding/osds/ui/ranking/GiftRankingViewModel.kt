package com.giftfunding.osds.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giftfunding.osds.R
import com.giftfunding.osds.data.response.home.item.ItemResponse

class GiftRankingViewModel : ViewModel() {

    private val _giftResponse = MutableLiveData<List<ItemResponse>>()
    val giftResponse: LiveData<List<ItemResponse>>
        get() = _giftResponse

    fun getGiftList() {
        // dummy data
        val list = ArrayList<ItemResponse>()
//        for (idx in 1..3) {
//            list.add(
//                ItemResponse(
//                    idx = idx,
//                    name = "상품$idx",
//                    price = 40000 + (idx * 1000),
//                    brand = "브랜드$idx",
//                    img = R.drawable.ic_launcher_background
//                )
//            )
//        }
        _giftResponse.value = list
    }

}