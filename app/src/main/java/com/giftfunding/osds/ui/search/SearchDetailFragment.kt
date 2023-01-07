package com.giftfunding.osds.ui.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentSearchDetailBinding
import com.giftfunding.osds.ui.search.adapter.PriceCategoryRecyclerViewAdapter
import com.giftfunding.osds.ui.search.adapter.SubCategoryRecyclerViewAdapter

class SearchDetailFragment : BaseFragment<FragmentSearchDetailBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_search_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init() {
        initSubCategory()
        initPriceCategory()

    }

    private fun initSubCategory() {
        val subCategory = resources.getStringArray(R.array.gift_luxury_sub_category)
        val subCategoryRecyclerViewAdapter = SubCategoryRecyclerViewAdapter()
        binding.contentSearchCategory.rvSubCategory.apply {
            adapter = subCategoryRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        subCategoryRecyclerViewAdapter.setItems(subCategory)
    }

    private fun initPriceCategory() {
        val priceCategory = resources.getStringArray(R.array.price_merchandise_category)
        val priceCategoryRecyclerViewAdapter = PriceCategoryRecyclerViewAdapter()
        binding.contentSearchCategory.rvPriceCategory.apply {
            adapter = priceCategoryRecyclerViewAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        priceCategoryRecyclerViewAdapter.setItems(priceCategory)
    }


    override fun initEvent() {

    }

    override fun initObserverEvent() {

    }
}