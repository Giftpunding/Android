package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemHomeMoreGiftListBinding
import com.giftfunding.osds.util.addComma

class HomeMoreGiftAdapter() :
    RecyclerView.Adapter<HomeMoreGiftAdapter.HomeMoreGiftHolder>() {

    private val itemList = mutableListOf<ItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMoreGiftHolder {
        val view =
            ItemHomeMoreGiftListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMoreGiftHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMoreGiftAdapter.HomeMoreGiftHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = MORE_GIFT_LIST_COUNT

    fun addItemList(moreGiftList : List<ItemResponse>){
        itemList.addAll(moreGiftList)
    }

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

    companion object {
        private const val MORE_GIFT_LIST_COUNT = 6
    }
}