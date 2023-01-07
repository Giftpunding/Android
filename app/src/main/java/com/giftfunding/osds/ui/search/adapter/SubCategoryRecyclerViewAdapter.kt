package com.giftfunding.osds.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ItemSearchSubCategoryBinding

class SubCategoryRecyclerViewAdapter :
    RecyclerView.Adapter<SubCategoryRecyclerViewAdapter.ViewHolder>() {
    private val subCategorys = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(subCategorys[position])
    }

    override fun getItemCount(): Int = subCategorys.size

    fun setItems(items: Array<String>) {
        subCategorys.addAll(items)
    }

    class ViewHolder(private val binding: ItemSearchSubCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: String) {
            binding.tvSubCategory.text = category
        }
    }
}