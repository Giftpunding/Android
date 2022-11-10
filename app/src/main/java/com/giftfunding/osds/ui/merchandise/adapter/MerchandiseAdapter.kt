package com.giftfunding.osds.ui.merchandise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.data.repository.remote.datasource.dto.home.item.ItemResponse
import com.giftfunding.osds.databinding.ItemMerchandiseHorizontalBinding
import com.giftfunding.osds.util.addComma

class MerchandiseAdapter(
    private val addGiftList: () -> Unit,
    private val moveActivity: () -> Unit
) : RecyclerView.Adapter<MerchandiseAdapter.HomeMerchandiseHolder>() {

    private val list = mutableListOf<ItemResponse>()

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

                Glide.with(binding.ivMerchandiseImg)
                    .load(img)
                    .centerCrop()
                    .override(100, 100)
                    .into(binding.ivMerchandiseImg)

                binding.btnMerchandiseAdd.setOnClickListener {
                    //선물 추가
                    addGiftList()
                }
                itemView.setOnClickListener {
                    moveActivity()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list.size > 5) 5
        else list.size
    }

    fun addItemList(itemList: List<ItemResponse>) {
        list.addAll(itemList)
    }
}
