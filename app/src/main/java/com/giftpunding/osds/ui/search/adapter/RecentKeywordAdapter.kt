package com.giftpunding.osds.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R

class RecentKeywordAdapter:
    RecyclerView.Adapter<RecentKeywordAdapter.RecentKeyWordViewHolder>() {

    private lateinit var mKeywordItemList: ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentKeyWordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recently_keyword, parent, false)
        return RecentKeyWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentKeyWordViewHolder, position: Int) {
        this.mKeywordItemList[position]?.let{holder.bind(it)}
    }

    override fun getItemCount(): Int {
        return this.mKeywordItemList.size
    }

    fun addItems(itemList: ArrayList<String>){
        this.mKeywordItemList = itemList
    }

    class RecentKeyWordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var keywordTextView: TextView
        fun bind(keyword: String) {
            keywordTextView = itemView.findViewById(R.id.tv_item_keyword)
            keywordTextView.text = keyword
        }
    }
}