package com.giftfunding.osds.ui.merchandise.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.repository.remote.datasource.dto.merchandise.ColorInfo
import com.giftfunding.osds.databinding.ItemMerchandiseColorBinding

class MerchandiseColorAdapter(
    private val selectColor: (ColorInfo) -> Unit
) : RecyclerView.Adapter<MerchandiseColorAdapter.MerchandiseColorHolder>() {

    private val colors = mutableListOf<ColorInfo>()

    inner class MerchandiseColorHolder(val binding: ItemMerchandiseColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseColorHolder {
        val view = ItemMerchandiseColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MerchandiseColorHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    fun addItems(items: List<ColorInfo>){
        colors.addAll(items)
    }

    override fun onBindViewHolder(holder: MerchandiseColorHolder, position: Int) {
        with(holder){
            binding.tvProductName.text = colors[position].colorName
            binding.vProductColor.setBackgroundColor(Color.parseColor(colors[position].color))
        }

        holder.itemView.setOnClickListener {
                selectColor(colors[position])
        }
    }
}