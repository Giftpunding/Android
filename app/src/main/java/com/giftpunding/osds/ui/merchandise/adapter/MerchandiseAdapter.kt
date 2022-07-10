package com.giftpunding.osds.ui.merchandise.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftpunding.osds.ui.merchandise.MerchandiseActivity
import com.giftpunding.osds.util.addComma


class MerchandiseAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MerchandiseAdapter.HomeMerchandiseHolder>() {

    private val list = mutableListOf<MerchandiseResponse>()

    inner class HomeMerchandiseHolder(val binding: ItemMerchandiseHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMerchandiseHolder {
        val view = ItemMerchandiseHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeMerchandiseHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMerchandiseHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvMerchandiseIdx.text = "${position + 1}"
                binding.tvMerchandiseBrand.text = brand
                binding.tvMerchandiseName.text = name
                binding.tvMerchandisePrice.text = addComma(price)

                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .override(100, 100)
                    .into(binding.ivMerchandiseImg)

                binding.btnMerchandiseAdd.setOnClickListener {
                    //선물 추가
                }
                itemView.setOnClickListener {
                    context.startActivity(Intent(context, MerchandiseActivity::class.java))
                }
            }
        }
    }

    override fun getItemCount():Int{
        return if(list.size > 5) 5
        else list.size
    }

    fun addItemList(itemList: List<MerchandiseResponse>) {
        list.addAll(itemList)
    }
}
