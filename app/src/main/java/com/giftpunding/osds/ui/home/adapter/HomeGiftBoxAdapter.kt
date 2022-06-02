package com.giftpunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.home.HomeGiftBoxResponse
import com.giftpunding.osds.databinding.ItemHomeGiftBoxListBinding

class HomeGiftBoxAdapter(private val context: Context, private val giftBoxList: List<HomeGiftBoxResponse>) :
    RecyclerView.Adapter<HomeGiftBoxAdapter.HomeGiftBoxHolder>() {
    inner class HomeGiftBoxHolder(binding: ItemHomeGiftBoxListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGiftBoxHolder {
        val view = ItemHomeGiftBoxListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeGiftBoxHolder(view)
    }

    override fun onBindViewHolder(holder: HomeGiftBoxHolder, position: Int) {
        with(holder) {
            with(giftBoxList[position]) {
                //데이터 연결
            }
        }
    }

    override fun getItemCount() = giftBoxList.size
}