package com.giftfunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ItemMostSearchedListBinding

class HomeMostSearchedListAdapter(val context : Context, val keywordList : List<String>) : RecyclerView.Adapter<HomeMostSearchedListAdapter.HomeMostSearchedListHolder>() {
    class HomeMostSearchedListHolder(val binding : ItemMostSearchedListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(keyword : String){
            binding.apply {
                if(adapterPosition > 9) divMostSearchedKeyword.visibility = View.GONE
                tvListNum.text = "${adapterPosition+1}"
                tvMostSearchedKeyword.text = keyword
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMostSearchedListHolder {
        val view = ItemMostSearchedListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeMostSearchedListHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMostSearchedListHolder, position: Int){
        holder.bind(keywordList[position])
    }

    override fun getItemCount() = 10
}