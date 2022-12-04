package com.giftfunding.osds.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemHomeLuxuryListBinding
import com.giftfunding.osds.util.addComma

class LuxuryGiftRecyclerViewAdapter :
    RecyclerView.Adapter<LuxuryGiftRecyclerViewAdapter.ViewHolder>() {

    private var luxuryGiftItems = mutableListOf<ItemResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemHomeLuxuryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(luxuryGiftItems[position])
    }

    override fun getItemCount(): Int = LUXURY_LIST_ITEM_COUNT

    fun setItems(list: List<ItemResponse>) {
        luxuryGiftItems = list as MutableList<ItemResponse>
    }

    class ViewHolder(private val binding: ItemHomeLuxuryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: ItemResponse) {
            Glide.with(binding.ivLuxuryImg.context)
                .load(item.img)
            binding.tvLuxuryBrand.text = item.brand
            binding.tvLuxuryPrice.text = addComma(item.price)
            binding.tvLuxuryItemName.text = item.name
        }
    }

    companion object {
        private const val LUXURY_LIST_ITEM_COUNT = 12
    }
}