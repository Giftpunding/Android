package com.giftfunding.osds.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseBasketGiftBinding
import com.giftfunding.osds.util.addComma

class BasketGiftRecyclerViewAdapter : RecyclerView.Adapter<BasketGiftRecyclerViewAdapter.ViewHolder>() {
    private var basketGiftItems = mutableListOf<ItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemMerchandiseBasketGiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(basketGiftItems[position])
    }

    override fun getItemCount(): Int = basketGiftItems.size

    fun setItems(list: List<ItemResponse>) {
        basketGiftItems = list as MutableList<ItemResponse>
    }

    class ViewHolder(private val binding: ItemMerchandiseBasketGiftBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: ItemResponse) {
            Glide.with(binding.ivMerchandiseImg.context)
                .load(item.img)
                .centerCrop()

            binding.tvBrandName.text = item.brand
            binding.tvItemPrice.text = addComma(item.price)
            binding.tvMerchandiseName.text = item.name
        }
    }

    companion object {
        private const val MORE_GIFT_LIST_COUNT = 6
    }
}