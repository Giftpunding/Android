package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_home
    private val labelList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
        binding.contentMostSelectGift.osdsRangeSlider.apply {
            labelList.add("1만원")
            labelList.add("5만원")
            labelList.add("10만원")
            labelList.add("20만원")
            setLabels(labelList)
        }
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init(){

    }

    override fun initEvent() {

    }

    override fun initObserverEvent() {
    }
}