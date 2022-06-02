package com.giftpunding.osds.ui.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.ui.search.adapter.*

class SearchActivity : AppCompatActivity() {

    private lateinit var giftCategoryImageRecyclerView: RecyclerView
    private lateinit var recentKeywordRecyclerView: RecyclerView
    private lateinit var popularityKeywordRecyclerView: RecyclerView

    private lateinit var searchEditText: EditText
    private lateinit var cancelTextView: TextView
    private lateinit var searchLayout: View
    private lateinit var searchKeyWordLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initialized()
        initRecyclerView()

        searchEditText.setOnFocusChangeListener { _, focus ->
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
    }

    private fun initialized() {
        searchEditText = findViewById(R.id.edit_search)
        cancelTextView = findViewById(R.id.tv_cancel)
        giftCategoryImageRecyclerView = findViewById(R.id.rv_category)

        searchLayout = findViewById(R.id.l_content_search)
        searchKeyWordLayout = findViewById(R.id.l_content_search_keyword)

        recentKeywordRecyclerView = findViewById(R.id.rv_recently_keyword)
        popularityKeywordRecyclerView = findViewById(R.id.rv_popular_keyword)
    }

    private fun initRecyclerView() {
        giftCategoryImageRecyclerView.apply {
            val categoryImageAdapter = CategoryImageAdapter()

            val categoryImageList = arrayListOf(
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
                ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null),
            )

            adapter = categoryImageAdapter
            layoutManager = GridLayoutManager(this@SearchActivity, 3)
            addItemDecoration(CategoryImageAdapterDecoration())
            categoryImageAdapter.addItems(categoryImageList)
        }

        recentKeywordRecyclerView.apply {
            val recentKeywordAdapter = RecentKeywordAdapter()
            val recentKeywordList = arrayListOf(
                "test",
                "test1",
                "test2",
                "test3",
                "test4"
            )

            adapter = recentKeywordAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            recentKeywordAdapter.addItems(recentKeywordList)
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
}
