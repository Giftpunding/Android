package com.giftfunding.osds.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.R
import com.giftfunding.osds.databinding.ItemRecentlyKeywordBinding

class RecentKeywordAdapter(private val onItemClick: (adapterPosition: Int) -> Unit) :
    RecyclerView.Adapter<RecentKeywordAdapter.RecentKeyWordViewHolder>() {

    private val recentKeywords = ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentKeyWordViewHolder = RecentKeyWordViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recently_keyword,
            parent,
            false
        ),
        onItemClick
    )

    override fun onBindViewHolder(holder: RecentKeyWordViewHolder, position: Int) {
        recentKeywords[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = recentKeywords.size

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<String>) {
        if (recentKeywords.isNotEmpty()) {
            recentKeywords.clear()
        }
        recentKeywords.addAll(items)
        notifyDataSetChanged()
    }

    class RecentKeyWordViewHolder(
        private val binding: ItemRecentlyKeywordBinding,
        private val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: String) {
            binding.tvItemKeyword.text = keyword
            binding.ivCancel.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }
}