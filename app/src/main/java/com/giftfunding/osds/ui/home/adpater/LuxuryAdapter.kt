package com.giftfunding.osds.ui.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.response.home.luxuryGift.LuxuryGiftResponse
import com.giftfunding.osds.databinding.ItemLuxuryListBinding
import com.giftfunding.osds.util.addComma

class LuxuryAdapter(val context: Context) : RecyclerView.Adapter<LuxuryAdapter.LuxuryHolder>() {

    private val list = mutableListOf<LuxuryGiftResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LuxuryHolder {
        val view = ItemLuxuryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LuxuryHolder(view)
    }

    override fun onBindViewHolder(holder: LuxuryHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(binding.ivMerchandiseImg)

                Glide.with(context)
                    .load(brand)
                    .centerCrop()
                    .into(binding.ivLuxuryBrand)

                binding.tvLuxuryMerchandiseName.text = name
                binding.tvLuxuryPrice.text = addComma(price)
            }
        }
    }

    override fun getItemCount() = list.size


    fun addItemList(getList: List<LuxuryGiftResponse>) {
        list.addAll(getList)
    }

    inner class LuxuryHolder(val binding: ItemLuxuryListBinding) :
        RecyclerView.ViewHolder(binding.root)
}