package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ItemMerchandiseMostKeywordBinding
import com.giftfunding.osds.ui.model.MostSearchKeyword

class MostKeywordGiftRecyclerViewAdapter :
    RecyclerView.Adapter<MostKeywordGiftRecyclerViewAdapter.ViewHolder>() {
    private val keywordItems = mutableListOf<MostSearchKeyword>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemMerchandiseMostKeywordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(keywordItems[position])
    }

    override fun getItemCount(): Int = MOST_SEARCHED_KEYWORD_COUNT

    // 왼쪽 순 부터 top 10
    fun setItems(items: MutableList<MostSearchKeyword>) {
        val rankTopFive = mutableListOf(items[0], items[1], items[2], items[3], items[4])
        val rankTopTen = mutableListOf(items[5], items[6], items[7], items[8], items[9])

        for (i in 0 until items.size / 2) {
            keywordItems.add(rankTopFive[i])
            keywordItems.add(rankTopTen[i])
        }
    }

    class ViewHolder(private val binding: ItemMerchandiseMostKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: MostSearchKeyword) {
            if (adapterPosition > 9) {
                binding.divMostSearchedKeyword.visibility = View.GONE
            }

            binding.tvListNum.text = item.rank.toString()
            binding.tvMostSearchedKeyword.text = item.keyword
        }
    }

    companion object {
        private const val MOST_SEARCHED_KEYWORD_COUNT = 10
    }
}