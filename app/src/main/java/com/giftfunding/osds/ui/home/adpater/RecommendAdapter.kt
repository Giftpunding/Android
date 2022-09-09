package com.giftfunding.osds.ui.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftfunding.osds.databinding.ItemSmallLuxuryListBinding
import com.giftfunding.osds.util.addComma

class RecommendAdapter(val context: Context) :
    RecyclerView.Adapter<RecommendAdapter.RecommendHolder>() {

    private val list = mutableListOf<MerchandiseResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendAdapter.RecommendHolder {
        val view = ItemSmallLuxuryListBinding.inflate(
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

    inner class RecommendHolder(val binding: ItemSmallLuxuryListBinding) :
        RecyclerView.ViewHolder(binding.root)
}