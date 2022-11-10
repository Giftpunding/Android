package com.giftfunding.osds.ui.merchandise.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.Merchandise
import com.giftfunding.osds.databinding.ItemMerchandiseReceiptBinding

class MerchandiseReceiptAdapter: RecyclerView.Adapter<MerchandiseReceiptAdapter.MerchandiseReceiptHolder>() {

    private val products = mutableListOf<Merchandise>()

    class MerchandiseReceiptHolder(val binding : ItemMerchandiseReceiptBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseReceiptHolder {
        val view = ItemMerchandiseReceiptBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MerchandiseReceiptHolder(view)
    }

    override fun onBindViewHolder(holder: MerchandiseReceiptHolder, position: Int) {
        with(holder){
            binding.vMerchandiseColor.setBackgroundColor(Color.parseColor(products[position].colorInfo.color))
            binding.tvMerchandiseColorName.text = products[position].colorInfo.colorName
            binding.tvMerchandiseSize.text = products[position].sizeInfo.size
        }
    }

    fun addItems(item: List<Merchandise>){
        if(products.isNotEmpty()){
            products.clear()
        }

        products.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = products.size

}