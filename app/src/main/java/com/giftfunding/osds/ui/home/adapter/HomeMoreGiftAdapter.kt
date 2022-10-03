package com.giftfunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.response.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemHomeMoreGiftListBinding
import com.giftfunding.osds.util.addComma

class HomeMoreGiftAdapter(val context: Context, val itemList: List<ItemResponse>) :
    RecyclerView.Adapter<HomeMoreGiftAdapter.HomeMoreGiftHolder>() {
    class HomeMoreGiftHolder(val binding: ItemHomeMoreGiftListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemResponse) {
            binding.apply {
                Glide.with(ivMerchandiseImg)
                    .load(item.img)
                    .centerCrop()

                tvBrandName.text = item.brand
                tvItemPrice.text = addComma(item.price)
                tvMerchandiseName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMoreGiftHolder {
        val view =
            ItemHomeMoreGiftListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMoreGiftHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMoreGiftAdapter.HomeMoreGiftHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = MORE_GIFT_LIST_COUNT

    companion object {
        private const val MORE_GIFT_LIST_COUNT = 6
    }
}