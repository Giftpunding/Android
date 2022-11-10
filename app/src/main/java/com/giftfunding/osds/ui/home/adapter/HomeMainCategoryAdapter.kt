package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemCategoryResponse
import com.giftfunding.osds.databinding.ItemHomeMainCategoryBinding


class HomeMainCategoryAdapter() : RecyclerView.Adapter<HomeMainCategoryAdapter.HomeMainCategoryHolder>() {

    private val categoryList = mutableListOf<ItemCategoryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMainCategoryHolder {
        val view = ItemHomeMainCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeMainCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMainCategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size

    fun addItemLIST(itemList : List<ItemCategoryResponse>){
        categoryList.addAll(itemList)
    }

    class HomeMainCategoryHolder(val binding : ItemHomeMainCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(categoryItem : ItemCategoryResponse){
            binding.apply {
                Glide.with(ivCategoryImg)
                    .load(categoryItem.img)
                    .centerCrop()

                tvCategory.text = categoryItem.categoryName
            }
        }
    }
}