package com.giftfunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.response.home.item.ItemLuxuryResponse
import com.giftfunding.osds.databinding.ItemHomeLuxuryListBinding
import com.giftfunding.osds.util.addComma

class HomeLuxuryListAdapter(
    val context: Context,
    private val homeLuxuryList: List<ItemLuxuryResponse>
) : RecyclerView.Adapter<HomeLuxuryListAdapter.HomeLuxuryListHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLuxuryListHolder {
        val view =
            ItemHomeLuxuryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeLuxuryListHolder(view)
    }

    override fun onBindViewHolder(holder: HomeLuxuryListHolder, position: Int) {
        holder.bind(homeLuxuryList[position])
    }

    override fun getItemCount() = 12

}