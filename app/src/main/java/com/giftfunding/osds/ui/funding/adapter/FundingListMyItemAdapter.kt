package com.giftfunding.osds.ui.funding.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.funding.FundingCompleteResponse
import com.giftfunding.osds.databinding.ItemFundingListBinding
import com.giftfunding.osds.util.addComma

class FundingListMyItemAdapter(val context: Context, val list: List<FundingCompleteResponse>) :
    RecyclerView.Adapter<FundingListMyItemAdapter.FundingListInfoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundingListInfoHolder {
        val view =
            ItemFundingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FundingListInfoHolder(view)
    }

    override fun onBindViewHolder(holder: FundingListInfoHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class FundingListInfoHolder(val binding: ItemFundingListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FundingCompleteResponse) {
            binding.apply {
                tvFundingPrice.text = addComma(data.fundingPrice)
                tvMerchandiseBrand.text = data.brand
                tvMerchandiseName.text = data.name

                Glide.with(ivMerchandiseImg)
                    .load(data.img)
                    .centerInside()
                    .into(ivMerchandiseImg)

                btnDetailInfo.setOnClickListener {
//                    ivMerchandiseImg.context.startActivity(Intent(ivMerchandiseImg.context, FundingResultActivity::class.java))
                }
            }
        }
    }
}