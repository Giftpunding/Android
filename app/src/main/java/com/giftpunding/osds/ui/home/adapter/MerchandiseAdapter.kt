package com.giftpunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ItemMerchandiseListBinding
import com.giftpunding.osds.util.addComma


class MerchandiseAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MerchandiseAdapter.HomeMerchandiseHolder>() {

    private val list = mutableListOf<HomeMerchandiseResponse>()

    inner class HomeMerchandiseHolder(val binding: ItemMerchandiseListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMerchandiseHolder {
        val view = ItemMerchandiseListBinding.inflate(
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
            }
        }
    }

    override fun getItemCount() = list.size

    fun addItemList(itemList: List<HomeMerchandiseResponse>) {
        list.addAll(itemList)
    }
}
