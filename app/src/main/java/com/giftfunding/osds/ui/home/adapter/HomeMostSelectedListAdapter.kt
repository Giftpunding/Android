package com.giftfunding.osds.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftfunding.osds.util.addComma

class HomeMostSelectedListAdapter() : RecyclerView.Adapter<HomeMostSelectedListAdapter.HomeMostSelectedViewHolder>(){

    private val itemList = mutableListOf<ItemResponse>()

    class HomeMostSelectedViewHolder(val binding : ItemMerchandiseHorizontalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemResponse){
            binding.apply {
                Glide.with(ivMerchandiseImg)
                    .load(item.img)
                    .override(300,300)
                    .centerCrop()

                tvMerchandiseIdx.text = item.idx.toString()
                tvMerchandiseBrand.text = item.brand
                tvMerchandiseName.text = item.name
                tvMerchandisePrice.text = addComma(item.price)

                btnMerchandiseAdd.setOnClickListener {
                    //TODO(선물에 담기)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMostSelectedViewHolder {
        val view = ItemMerchandiseHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeMostSelectedViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMostSelectedViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = MOST_SELECTED_ITEM_COUNT

    fun addItemList(itemList : List<ItemResponse>){
        this.itemList.addAll(itemList)
    }

    companion object{
        private const val MOST_SELECTED_ITEM_COUNT = 5
    }
}