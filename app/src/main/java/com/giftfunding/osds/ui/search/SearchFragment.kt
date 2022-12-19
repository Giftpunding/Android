package com.giftfunding.osds.ui.search

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentSearchBinding
import com.giftfunding.osds.ui.search.adapter.CategoryImageAdapter
import com.giftfunding.osds.util.RecyclerViewDeco

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun layoutResId(): Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initObserverEvent()
    }

    private fun init() {
        initGiftCategoryImage()
    }

    private fun initGiftCategoryImage() {

        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null)
        var categoryImages = ArrayList<Drawable>()
        drawable?.let {
            categoryImages = arrayListOf(
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable,
                drawable
            )
        }

        val categoryImageAdapter = CategoryImageAdapter()
        binding.rvGiftCategory.apply {
            adapter = categoryImageAdapter
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            addItemDecoration(RecyclerViewDeco(0f,44f,13f,13f))
        }
        categoryImageAdapter.setItems(categoryImages)
    }

    override fun initEvent() {

    }

    override fun initObserverEvent() {

    }

    companion object{
        private const val SPAN_COUNT: Int = 4
    }
}