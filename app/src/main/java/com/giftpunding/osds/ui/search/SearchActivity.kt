package com.giftpunding.osds.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.application.Application.Companion.searchRepository
import com.giftpunding.osds.base.DemoActivity
import com.giftpunding.osds.repository.local.pref.KeywordSharedPreference
import com.giftpunding.osds.ui.search.adapter.*
import com.giftpunding.osds.util.GridRecyclerViewDeco

class SearchActivity : DemoActivity(),
    TextView.OnEditorActionListener,
    OnItemClickListener,
    KeywordSharedPreference.Listener {

    override fun layoutRes() = R.layout.activity_search

    private lateinit var giftCategoryImageRecyclerView: RecyclerView
    private lateinit var recentKeywordRecyclerView: RecyclerView
    private val recentKeywordAdapter = RecentKeywordAdapter()

    private lateinit var popularityKeywordRecyclerView: RecyclerView

    private lateinit var searchEditText: EditText
    private lateinit var cancelTextView: TextView
    private lateinit var searchLayout: View
    private lateinit var searchKeyWordLayout: View
    private lateinit var clearRecentKeywordTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()
        initRecyclerView()

        searchRepository.init(this)

        searchEditText.setOnFocusChangeListener { view, focus ->
            if (focus) {
                cancelTextView.visibility = View.VISIBLE
                searchLayout.visibility = View.GONE
                searchKeyWordLayout.visibility = View.VISIBLE
            }
        }

        cancelTextView.setOnClickListener {
            cancelTextView.visibility = View.GONE
            searchEditText.clearFocus()
            searchLayout.visibility = View.VISIBLE
            searchKeyWordLayout.visibility = View.GONE
        }

        clearRecentKeywordTextView.setOnClickListener {
            searchRepository.deleteAllRecentKeyword()
        }
    }

    private fun initialized() {
        searchEditText = findViewById(R.id.edit_search)
        searchEditText.setOnEditorActionListener(this)

        cancelTextView = findViewById(R.id.tv_cancel)
        giftCategoryImageRecyclerView = findViewById(R.id.rv_category)

        searchLayout = findViewById(R.id.l_content_search)
        searchKeyWordLayout = findViewById(R.id.l_content_search_keyword)

        recentKeywordRecyclerView = findViewById(R.id.rv_recently_keyword)
        popularityKeywordRecyclerView = findViewById(R.id.rv_popular_keyword)

        clearRecentKeywordTextView = findViewById(R.id.tv_all_delete)
    }

    private fun initRecyclerView() {
        giftCategoryImageRecyclerView.apply {
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

        recentKeywordRecyclerView.apply {
            adapter = recentKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            recentKeywordAdapter.setOnclickListener(this@SearchActivity)
            recentKeywordAdapter.addItems(searchRepository.getRecentKeyword())
        }

        popularityKeywordRecyclerView.apply {
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
