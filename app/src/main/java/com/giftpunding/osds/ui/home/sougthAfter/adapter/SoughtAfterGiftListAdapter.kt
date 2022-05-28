package com.giftpunding.osds.ui.home.sougthAfter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.databinding.ItemMerchandiseVerticalBinding

class SoughtAfterGiftListAdapter(val context: Context) :
    RecyclerView.Adapter<SoughtAfterGiftListAdapter.SoughtAfterGiftListHolder>() {

    private val list = mutableListOf<MerchandiseResponse>()

    inner class SoughtAfterGiftListHolder(private val binding: ItemMerchandiseVerticalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoughtAfterGiftListHolder {
        val view = ItemMerchandiseVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SoughtAfterGiftListHolder(view)
    }

    override fun onBindViewHolder(holder: SoughtAfterGiftListHolder, position: Int) {
        with(holder){
            with(list[position]){

            }
        }
    }

    override fun getItemCount() = list.size

    public fun getItemList(getList: List<MerchandiseResponse>) {
        list.addAll(getList)
    }
}