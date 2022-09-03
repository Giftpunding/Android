package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.databinding.ItemAddressSearchBinding
import com.giftpunding.osds.ui.address.AddressSearchClickListener

class AddressSearchAdapter :
    RecyclerView.Adapter<AddressSearchAdapter.AddressSearchResultViewHolder>() {

    private var addressItems = ArrayList<AddressSearchResultDocumentResponse>()
    private lateinit var itemClickListener: AddressSearchClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AddressSearchResultViewHolder =
        AddressSearchResultViewHolder(
            ItemAddressSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AddressSearchResultViewHolder, position: Int) {
        holder.onBind(addressItems[position])
        holder.itemView.setOnClickListener{
            itemClickListener.addressSearchClickable(addressItems[position])
        }
    }

    override fun getItemCount(): Int = addressItems.size

    // 인터페이스 주입
    fun setItemClickListener(addressSearchClickListener: AddressSearchClickListener) {
        this.itemClickListener = addressSearchClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(address: List<AddressSearchResultDocumentResponse>) {
        addressItems.addAll(address)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        addressItems.clear()
        notifyDataSetChanged()
    }

    class AddressSearchResultViewHolder(
        private val binding: ItemAddressSearchBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            item: AddressSearchResultDocumentResponse,
        ) {
            binding.lAddressName.tvAddress.text = item.addressName
        }
    }
}

