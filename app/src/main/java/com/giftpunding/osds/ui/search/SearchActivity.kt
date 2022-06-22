package com.giftpunding.osds.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.application.Application.Companion.searchRepository
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivitySearchBinding
import com.giftpunding.osds.repository.local.pref.KeywordSharedPreference
import com.giftpunding.osds.ui.search.adapter.*
import com.giftpunding.osds.util.GridRecyclerViewDeco

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    TextView.OnEditorActionListener,
    OnItemClickListener,
    KeywordSharedPreference.Listener {

    private val recentKeywordAdapter = RecentKeywordAdapter()
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
        binding.editSearch.setOnFocusChangeListener { view, focus ->
            if (focus) {
                showKeywordLayout()
            }
        }

        binding.tvCancel.setOnClickListener {
            showCategoryLayout()
        }

        binding.lContentSearchKeyword.tvAllDelete.setOnClickListener {
            searchRepository.deleteAllRecentKeyword()
        }
    }

    private fun initGiftCategoryImageRecyclerView() {
        binding.lContentSearch.rvCategory.apply {
            val categoryImageAdapter = CategoryImageAdapter()

            val categoryImageList = arrayListOf(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_back, null),
            )

            adapter = categoryImageAdapter
            layoutManager = GridLayoutManager(this@SearchActivity, 4)
            addItemDecoration(GridRecyclerViewDeco(10, 40))
            categoryImageAdapter.addItems(categoryImageList)
        }
    }

    private fun initRecentKeywordRecyclerView() {
        binding.lContentSearchKeyword.rvRecentlyKeyword.apply {
            adapter = recentKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            recentKeywordAdapter.setOnclickListener(this@SearchActivity)
            recentKeywordAdapter.addItems(searchRepository.getRecentKeyword())
        }
    }

    private fun initPopularityKeywordRecyclerView() {
        binding.lContentSearchKeyword.rvPopularKeyword.apply {
            val popularityKeywordAdapter = PopularityKeywordAdapter()
            val popularityKeywordList = arrayListOf(
                "test0",
                "test1",
                "test2",
                "test3",
                "test4"
            )

            adapter = popularityKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            popularityKeywordAdapter.addItems(popularityKeywordList)
            addItemDecoration(PopularityKeywordAdapterDecoration())
        }
    }

    private fun showCategoryLayout() {
        binding.tvCancel.visibility = View.GONE
        binding.editSearch.clearFocus()
        binding.lContentSearch.root.visibility = View.VISIBLE
        binding.lContentSearchKeyword.root.visibility = View.GONE
    }

    private fun showKeywordLayout() {
        binding.tvCancel.visibility = View.VISIBLE
        binding.lContentSearch.root.visibility = View.GONE
        binding.lContentSearchKeyword.root.visibility = View.VISIBLE
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_SEARCH) {
            if (p0 != null) {
                searchRepository.addRecentKeyword(p0.text.toString())
            }
        }
        return true
    }

    override fun onClickItem(adapterPosition: Int) {
        Log.d(TAG, "delete recent keyword")
        searchRepository.deleteRecentKeyword(adapterPosition)
    }

    override fun complete(recentKeywordList: ArrayList<String>) {
        Log.d(TAG, "save Keyword SharedPreference")
        recentKeywordAdapter.addItems(recentKeywordList)
    }

    private companion object {
        private const val TAG: String = "SearchActivity..."
    }
}
