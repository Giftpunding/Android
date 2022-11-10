package com.giftfunding.osds.ui.merchandise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.SizeInfo
import com.giftfunding.osds.databinding.ItemMerchandiseSizeBinding
import com.giftfunding.osds.util.convertWon

class MerchandiseSizeAdapter(
    private val selectSize: (SizeInfo) -> Unit
) :
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
            binding.tvMerchandiseSizeSurcharge.text = convertWon(sizes[position].surCharge)
        }

        holder.itemView.setOnClickListener {
            selectSize(sizes[position])
        }
    }

    fun addItems(items: List<SizeInfo>){
        sizes.addAll(items)
    }

    override fun getItemCount(): Int = sizes.size
}