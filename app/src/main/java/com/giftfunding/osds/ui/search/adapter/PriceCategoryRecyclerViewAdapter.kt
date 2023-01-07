package com.giftfunding.osds.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ItemSearchSubCategoryBinding

class PriceCategoryRecyclerViewAdapter :
    RecyclerView.Adapter<PriceCategoryRecyclerViewAdapter.ViewHolder>() {

    private val priceCategorys = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(priceCategorys[position])
    }

    override fun getItemCount(): Int = priceCategorys.size

    fun setItems(items: Array<String>) {
        priceCategorys.addAll(items)
    }

    class ViewHolder (private val binding: ItemSearchSubCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(priceCategory: String) {
            binding.tvSubCategory.text = priceCategory
        }
    }
}