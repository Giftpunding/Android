package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemLuxuryResponse
import com.giftfunding.osds.databinding.ItemHomeLuxuryListBinding
import com.giftfunding.osds.util.addComma

class HomeLuxuryListAdapter(
) : RecyclerView.Adapter<HomeLuxuryListAdapter.HomeLuxuryListHolder>() {

    private val homeLuxuryList = mutableListOf<ItemLuxuryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLuxuryListHolder {
        val view =
            ItemHomeLuxuryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeLuxuryListHolder(view)
    }

    override fun onBindViewHolder(holder: HomeLuxuryListHolder, position: Int) {
        holder.bind(homeLuxuryList[position])
    }

    override fun getItemCount() = LUXURY_LIST_ITEM_COUNT

    fun addItemList(itemList : List<ItemLuxuryResponse>){
        homeLuxuryList.addAll(itemList)
    }

    class HomeLuxuryListHolder(val binding: ItemHomeLuxuryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemLuxuryResponse) {
            binding.apply {
                Glide.with(ivLuxuryBrand)
                    .load(item.brandImg)

                Glide.with(ivLuxuryImg)
                    .load(item.img)

                tvLuxuryPrice.text = addComma(item.price)
                tvLuxuryItemName.text = item.name
            }
        }
    }

    companion object {
        private const val LUXURY_LIST_ITEM_COUNT = 12
    }
}