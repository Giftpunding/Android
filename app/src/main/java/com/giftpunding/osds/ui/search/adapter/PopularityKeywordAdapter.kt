package com.giftpunding.osds.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ItemPopularityKeywordBinding

class PopularityKeywordAdapter :
    RecyclerView.Adapter<PopularityKeywordAdapter.PopularityViewHolder>() {

    private val mPopularityKeywordList = ArrayList<String>()

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
        this.mPopularityKeywordList[position].let { holder.bind(it, position) }
    }

    override fun getItemCount(): Int = this.mPopularityKeywordList.size

    fun addItems(keywordList: ArrayList<String>) {
        this.mPopularityKeywordList.addAll(keywordList.clone() as ArrayList<String>)
    }

    class PopularityViewHolder(private val binding: ItemPopularityKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(keyword: String, position: Int) {
            binding.tvPopularKeywordRank.text = (position + 1).toString()
            binding.tvPopularityKeyword.text = keyword
        }
    }
}