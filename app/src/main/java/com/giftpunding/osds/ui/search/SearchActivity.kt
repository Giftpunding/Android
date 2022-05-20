package com.giftpunding.osds.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.ui.search.adapter.CategoryImageAdapter
import com.giftpunding.osds.ui.search.adapter.CategoryImageAdapterDecoration

class SearchActivity : AppCompatActivity() {

    private lateinit var giftCategoryImageRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initialized()
        initRecyclerView()
    }

    private fun initialized() {
        giftCategoryImageRecyclerView = findViewById(R.id.rv_category)
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
    }

}