package com.giftpunding.osds.ui.home.sougthAfter.adapter

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterResponse
import com.giftpunding.osds.ui.home.sougthAfter.SoughtAfterListFragment

class SoughtAfterPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentSize: Int,
    list: List<SoughtAfterResponse>
) : FragmentStateAdapter(fragmentActivity) {

    private val slicedList = listSlice(list)

    override fun getItemCount() = fragmentSize

    override fun createFragment(position: Int): Fragment {
        val fragment = SoughtAfterListFragment.getFragmentInstance()
        fragment?.arguments = Bundle().apply {
            //데이터 담아주기.
            putParcelableArrayList("listData",slicedList[position] as ArrayList<out Parcelable>?)
        }
        return fragment!!
    }

    //리스트 4개씩 끊어서 사용
    private fun listSlice(dataList: List<SoughtAfterResponse>): ArrayList<List<SoughtAfterResponse>> {
        val list = arrayListOf<List<SoughtAfterResponse>>()
        var startIdx = 0
        var endIdx = 4

        for (idx in 0 until fragmentSize) {
            if (endIdx > dataList.size) {
                endIdx = dataList.size
            }
            val inputList = mutableListOf<SoughtAfterResponse>()
            for (inputIdx in startIdx until endIdx) {
                inputList.add(dataList[inputIdx])
            }
            startIdx = endIdx
            endIdx += 4
            list.add(inputList)
        }
        return list
    }
}