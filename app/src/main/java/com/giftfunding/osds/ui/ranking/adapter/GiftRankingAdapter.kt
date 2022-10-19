package com.giftfunding.osds.ui.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.response.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftfunding.osds.util.addComma

class GiftRankingAdapter :
    RecyclerView.Adapter<GiftRankingAdapter.GiftRankingViewHolder>() {

    private val itemList = mutableListOf<ItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftRankingViewHolder {
        val view = ItemMerchandiseHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GiftRankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: GiftRankingViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun addItems(items: List<ItemResponse>){
        itemList.addAll(items)
    }

    class GiftRankingViewHolder(val binding: ItemMerchandiseHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemResponse) {
            binding.apply {
                Glide.with(ivMerchandiseImg)
                    .load(item.img)
                    .override(300, 300)
                    .centerCrop()

                tvMerchandiseIdx.text = item.idx.toString()
                tvMerchandiseBrand.text = item.brand
                tvMerchandiseName.text = item.name
                tvMerchandisePrice.text = addComma(item.price)
            }
        }
    }
}