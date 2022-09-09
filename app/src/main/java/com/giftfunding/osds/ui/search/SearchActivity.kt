package com.giftfunding.osds.ui.search

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.application.Application.Companion.searchRepository
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivitySearchBinding
import com.giftfunding.osds.repository.local.pref.KeywordSharedPreference
import com.giftfunding.osds.ui.search.adapter.*
import com.giftfunding.osds.util.GridRecyclerViewDeco

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    TextView.OnEditorActionListener,
    KeywordSharedPreference.Listener {

    private val recentKeywordAdapter = RecentKeywordAdapter { position ->
        Log.d(TAG, "delete recent keyword")
        searchRepository.deleteRecentKeyword(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()
        initGiftCategoryImageRecyclerView()
        initRecentKeywordRecyclerView()
        initPopularityKeywordRecyclerView()

    }

    override fun init() {
        searchRepository.init(this)
    }

    override fun initEvent() {
        binding.editSearch.setOnEditorActionListener(this)

        binding.editSearch.setOnFocusChangeListener { _, focus ->
            changeLayout(focus)
            changeSearchIconVisibleState(focus)
        }

        binding.tvCancel.setOnClickListener {
            binding.editSearch.clearFocus()
            binding.editSearch.text = null
        }

        binding.lContentSearchKeyword.tvAllDelete.setOnClickListener {
            searchRepository.deleteAllRecentKeyword()
        }
    }

    private fun initGiftCategoryImageRecyclerView() {

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
        binding.lContentSearch.rvCategory.apply {
            adapter = categoryImageAdapter
            layoutManager = GridLayoutManager(this@SearchActivity, GRID_COLUMN_SIZE)
            addItemDecoration(GridRecyclerViewDeco(HORIZONTAL_SPACING, VERTICAL_SPACING))
        }
        categoryImageAdapter.addItems(categoryImages)
    }

    private fun initRecentKeywordRecyclerView() {
        binding.lContentSearchKeyword.rvRecentlyKeyword.apply {
            adapter = recentKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
        recentKeywordAdapter.addItems(searchRepository.getRecentKeyword())
    }

    private fun initPopularityKeywordRecyclerView() {
        val popularityKeywordList = arrayListOf(
            "test0",
            "test1",
            "test2",
            "test3",
            "test4"
        )

        val popularityKeywordAdapter = PopularityKeywordAdapter()
        binding.lContentSearchKeyword.rvPopularKeyword.apply {
            adapter = popularityKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(PopularityKeywordAdapterDecoration())
        }
        popularityKeywordAdapter.addItems(popularityKeywordList)
    }

    private fun changeLayout(focus: Boolean) {
        // focus가 없으면 카테고리 레이아웃, 있으면 키워드 레이아웃
        binding.tvCancel.visibility = if (focus) View.VISIBLE else View.GONE
        binding.lContentSearch.root.visibility = if (focus) View.GONE else View.VISIBLE
        binding.lContentSearchKeyword.root.visibility = if (focus) View.VISIBLE else View.GONE
    }

    private fun changeSearchIconVisibleState(focus: Boolean) {
        if (focus) {
            binding.editSearch.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )
        } else {
            binding.editSearch.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_search,
                0,
                0,
                0
            )
        }
    }

    override fun onEditorAction(view: TextView?, viewId: Int, p2: KeyEvent?): Boolean {
        if (viewId == EditorInfo.IME_ACTION_SEARCH) {
            view?.let {
                searchRepository.addRecentKeyword(it.text.toString())
            }
        }
        return true
    }

    override fun complete(recentKeywordList: ArrayList<String>) {
        Log.d(TAG, "save Keyword SharedPreference")
        recentKeywordAdapter.addItems(recentKeywordList)
    }

    private companion object {
        private const val TAG: String = "SearchActivity..."
        private const val GRID_COLUMN_SIZE: Int = 4
        private const val HORIZONTAL_SPACING: Int = 10
        private const val VERTICAL_SPACING: Int = 40
    }
}
