package com.giftpunding.osds.ui.home.adpater

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.data.response.home.luxury.LuxuryResponse
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.databinding.ItemLuxuryListBinding
import com.giftpunding.osds.databinding.ItemMerchandiseVerticalBinding
import com.giftpunding.osds.util.addComma

class RecommendAdapter(val context: Context) :
    RecyclerView.Adapter<RecommendAdapter.RecommendHolder>() {

    private val list = mutableListOf<MerchandiseResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendAdapter.RecommendHolder {
        val view = ItemMerchandiseVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendAdapter.RecommendHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(binding.ivSoughtAfterImg)

                binding.tvSoughtAfeterBrand.text = brand
                binding.tvSoughtAfterMerchandiseName.text = name
                binding.tvSoughtAfterPrice.text = addComma(price)
            }
        }
    }

    override fun getItemCount() = list.size


    fun addItemList(getList: List<MerchandiseResponse>) {
        list.addAll(getList)
    }

    inner class RecommendHolder(val binding: ItemMerchandiseVerticalBinding) :
        RecyclerView.ViewHolder(binding.root)
}