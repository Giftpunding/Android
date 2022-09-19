package com.giftfunding.osds.ui.merchandise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.response.product.SizeInfo
import com.giftfunding.osds.databinding.ItemMerchandiseSizeBinding

class MerchandiseSizeAdapter :
    RecyclerView.Adapter<MerchandiseSizeAdapter.MerchandiseSizeHolder>() {

    private val sizes = mutableListOf<SizeInfo>()

    inner class MerchandiseSizeHolder(val binding: ItemMerchandiseSizeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseSizeHolder {
        val view = ItemMerchandiseSizeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MerchandiseSizeHolder(view)
    }

    override fun onBindViewHolder(holder: MerchandiseSizeHolder, position: Int) {
        with(holder){
            binding.tvMerchandiseSizeName.text = sizes[position].size
            binding.tvMerchandiseSizeSurcharge.text = sizes[position].surCharge
        }
    }

    fun addItems(items: List<SizeInfo>){
        sizes.addAll(items)
    }

    override fun getItemCount(): Int = sizes.size
}