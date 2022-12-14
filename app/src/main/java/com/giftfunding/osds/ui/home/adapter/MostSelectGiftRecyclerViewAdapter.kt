package com.giftfunding.osds.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftfunding.osds.util.addComma

class MostSelectGiftRecyclerViewAdapter :
    RecyclerView.Adapter<MostSelectGiftRecyclerViewAdapter.ViewHolder>() {
    private val mostSelectGiftItems = mutableListOf<ItemResponse>()
    private var listSize = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMerchandiseHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mostSelectGiftItems[position])
    }

    override fun getItemCount(): Int = listSize

    fun setFullSize(fullSize: Boolean) {
        listSize = if (fullSize) mostSelectGiftItems.size else 5
    }

    fun setItems(list: List<ItemResponse>) {
        mostSelectGiftItems.addAll(list)
    }

    class ViewHolder(private val binding: ItemMerchandiseHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ItemResponse) {
            Glide.with(binding.ivMerchandiseImg.context)
                .load(item.img)
                .override(300, 300)
                .centerCrop()
                .into(binding.ivMerchandiseImg)

            binding.tvMerchandiseIdx.text = item.idx.toString()
            binding.tvMerchandiseBrand.text = item.brand
            binding.tvMerchandiseName.text = item.name
            binding.tvMerchandisePrice.text = addComma(item.price)
        }
    }
}