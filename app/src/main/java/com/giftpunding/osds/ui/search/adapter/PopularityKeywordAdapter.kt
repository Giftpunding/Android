package com.giftpunding.osds.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ItemPopularityKeywordBinding

class PopularityKeywordAdapter :
    RecyclerView.Adapter<PopularityKeywordAdapter.PopularityViewHolder>() {

    private val popularityKeywords = ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularityViewHolder = PopularityViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_popularity_keyword,
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: PopularityViewHolder,
        position: Int
    ) {
        popularityKeywords[position].let { holder.bind(it, position) }
    }

    override fun getItemCount(): Int = popularityKeywords.size

    fun addItems(items: List<String>) {
        popularityKeywords.addAll(items)
    }

    class PopularityViewHolder(
        private val binding: ItemPopularityKeywordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: String, position: Int) {
            val rank = position + 1
            binding.tvPopularKeywordRank.text = "$rank"
            binding.tvPopularityKeyword.text = keyword
        }
    }
}