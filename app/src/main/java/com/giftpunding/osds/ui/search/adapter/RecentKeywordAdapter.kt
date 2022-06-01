package com.giftpunding.osds.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R
import com.giftpunding.osds.ui.search.OnItemClickListener

class RecentKeywordAdapter :
    RecyclerView.Adapter<RecentKeywordAdapter.RecentKeyWordViewHolder>() {

    private lateinit var mKeywordItemList: ArrayList<String>
    private lateinit var onItemClick: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentKeyWordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recently_keyword, parent, false)
        return RecentKeyWordViewHolder(view, this.onItemClick)
    }

    override fun onBindViewHolder(holder: RecentKeyWordViewHolder, position: Int) {
        this.mKeywordItemList[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return this.mKeywordItemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(itemList: ArrayList<String>) {
        this.mKeywordItemList = itemList
        notifyDataSetChanged()
    }

    fun setOnclickListener(onItemClick: OnItemClickListener) {
        this.onItemClick = onItemClick
    }

    class RecentKeyWordViewHolder(itemView: View, private val onItemClick: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var keywordTextView: TextView
        private lateinit var cancelImageView: ImageView

        fun bind(keyword: String) {
            keywordTextView = itemView.findViewById(R.id.tv_item_keyword)
            keywordTextView.text = keyword

            cancelImageView = itemView.findViewById(R.id.iv_cancel)
            cancelImageView.setOnClickListener {
                onItemClick.onClickItem(adapterPosition)
            }
        }
    }
}