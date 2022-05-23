package com.giftpunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.home.HomeGiftBoxResponse
import com.giftpunding.osds.databinding.ItemHomeGiftBoxListBinding

class HomeGiftBoxAdapter(
    private val context: Context,
) : RecyclerView.Adapter<HomeGiftBoxAdapter.HomeGiftBoxHolder>() {

    private val list = mutableListOf<HomeGiftBoxResponse>()

    inner class HomeGiftBoxHolder(binding: ItemHomeGiftBoxListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGiftBoxHolder {
        val view =
            ItemHomeGiftBoxListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeGiftBoxHolder(view)
    }

    override fun onBindViewHolder(holder: HomeGiftBoxHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                //데이터 연결
            }
        }
    }

    override fun getItemCount() = list.size

    fun addItemList(itemList: List<HomeGiftBoxResponse>) {
        list.addAll(itemList)
    }
}