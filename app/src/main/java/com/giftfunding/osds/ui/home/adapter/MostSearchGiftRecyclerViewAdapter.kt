package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ItemMostSearchGiftBinding

class MostSearchGiftRecyclerViewAdapter :
    RecyclerView.Adapter<MostSearchGiftRecyclerViewAdapter.ViewHolder>() {

    private val mostSearchGiftItems = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemMostSearchGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mostSearchGiftItems[position])
    }

    override fun getItemCount(): Int = mostSearchGiftItems.size

    fun setItems(list: List<Int>) {
        mostSearchGiftItems.addAll(list)
    }

    class ViewHolder(private val binding: ItemMostSearchGiftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Int) {
            binding.ivMostSearchGift.setImageResource(item)
        }
    }
}