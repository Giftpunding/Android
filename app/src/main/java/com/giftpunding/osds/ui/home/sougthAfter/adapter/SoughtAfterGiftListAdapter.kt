package com.giftpunding.osds.ui.home.sougthAfter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterResponse
import com.giftpunding.osds.databinding.ItemMerchandiseVerticalBinding
import com.giftpunding.osds.util.addComma
import java.util.ArrayList

class SoughtAfterGiftListAdapter(val context: Context) :
    RecyclerView.Adapter<SoughtAfterGiftListAdapter.SoughtAfterGiftListHolder>() {

    private val list = arrayListOf<SoughtAfterResponse>()

    inner class SoughtAfterGiftListHolder(val binding: ItemMerchandiseVerticalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoughtAfterGiftListHolder {
        val view = ItemMerchandiseVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SoughtAfterGiftListHolder(view)
    }

    override fun onBindViewHolder(holder: SoughtAfterGiftListHolder, position: Int) {
        with(holder){
            with(list[position]){
                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(binding.ivSoughtAferImg)

                binding.tvSoughtAfterPrice.text = addComma(price)
                binding.tvSoughtAfterMerchandiseName.text = name
                binding.tvSoughtAfeterBrand.text = brand

                //선물 추가
                binding.btnMerchandiseAdd.setOnClickListener {

                }
            }
        }
    }

    override fun getItemCount() = list.size

    fun addItemList(getList: ArrayList<SoughtAfterResponse>?) {
        if (getList != null) {
            list.addAll(getList)
        }
    }
}