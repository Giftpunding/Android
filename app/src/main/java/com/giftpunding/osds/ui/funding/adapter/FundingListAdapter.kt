package com.giftpunding.osds.ui.funding.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.data.response.funding.FundingResponse
import com.giftpunding.osds.databinding.ItemFundingGiftListBinding
import com.giftpunding.osds.ui.funding.FundingActivity
import com.giftpunding.osds.util.addComma

class FundingListAdapter(val context: Context) : RecyclerView.Adapter<FundingListAdapter.FundingHolder>() {

    private val list = mutableListOf<FundingResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundingHolder {
        val view =
            ItemFundingGiftListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FundingHolder(view)
    }

    override fun onBindViewHolder(holder: FundingHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                val progress = possibleFunding.toDouble().div(totalPrice)

                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(binding.ivMerchandiseImg)

                binding.tvMerchandiseBrand.text = brand
                binding.tvMerchandiseName.text = name
                binding.tvFundingPercentage.text = "${(progress * 100).toInt()}%"
                binding.tvCurrentFundingPrice.text = addComma(nowFunding)
                binding.tvTotalFundingPrice.text = addComma(totalPrice)
                binding.pgFunding.progress = (progress * 100).toInt()
                binding.btnGiftTogether.setOnClickListener {
                    context.startActivity(Intent(context, FundingActivity::class.java))
                }
            }
        }
    }


    override fun getItemCount() = list.size

    fun addItemList(getList: List<FundingResponse>) {
        list.addAll(getList)
    }

    class FundingHolder(val binding: ItemFundingGiftListBinding) :
        RecyclerView.ViewHolder(binding.root)
}