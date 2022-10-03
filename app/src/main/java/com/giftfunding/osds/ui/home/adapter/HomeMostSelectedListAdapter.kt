package com.giftfunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.giftfunding.osds.data.response.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftfunding.osds.databinding.ItemMostSearchedListBinding
import com.giftfunding.osds.util.addComma

class HomeMostSelectedListAdapter(val context : Context, val itemList : List<ItemResponse>) : RecyclerView.Adapter<HomeMostSelectedListAdapter.HomeMostSelectedViewHolder>(){
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

    override fun getItemCount() = 5
}