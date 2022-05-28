package com.giftpunding.osds.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R

class PopularityKeywordAdapter: RecyclerView.Adapter<PopularityKeywordAdapter.PopularityViewHolder>() {

    private lateinit var mPopularityKeywordList: ArrayList<String>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularityKeywordAdapter.PopularityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popularity_keyword, parent, false)
        return PopularityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PopularityKeywordAdapter.PopularityViewHolder,
        position: Int
    ) {
        val itemSize = mPopularityKeywordList.size
        this.mPopularityKeywordList[position].let { holder.bind(it, itemSize) }
    }

    override fun getItemCount(): Int {
        return this.mPopularityKeywordList.size
    }

    fun addItems(keyword: ArrayList<String>){
        this.mPopularityKeywordList = keyword
    }

    class PopularityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var popularityKeywordTextView: TextView
        private lateinit var divider: View

        fun bind(keyword: String, itemSize: Int) {
            divider = itemView.findViewById(R.id.v_popularity_divider)
            popularityKeywordTextView = itemView.findViewById(R.id.tv_popularity_keyword)
            popularityKeywordTextView.text = keyword

            if(adapterPosition == itemSize - 1){
                divider.visibility = View.GONE
            }
        }
    }
}