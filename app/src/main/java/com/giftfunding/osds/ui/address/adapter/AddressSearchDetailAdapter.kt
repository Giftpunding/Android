package com.giftfunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftfunding.osds.databinding.ItemAddressSearchDetailBinding
import com.giftfunding.osds.ui.address.AddressSearchDetailClickListener

class AddressSearchDetailAdapter :
    RecyclerView.Adapter<AddressSearchDetailAdapter.AddressSearchDetailResultViewHolder>() {

    private lateinit var itemClickListener: AddressSearchDetailClickListener
    private var addressItems = ArrayList<AddressSearchResultDocumentResponse>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressSearchDetailResultViewHolder =
        AddressSearchDetailResultViewHolder(
            ItemAddressSearchDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AddressSearchDetailResultViewHolder, position: Int) {
        holder.onBind(addressItems[position])
        holder.itemView.setOnClickListener{
            itemClickListener.addressSearchDetailClickable(addressItems[position])
        }
    }

    override fun getItemCount(): Int = addressItems.size

    fun setClickListener(addressSearchClickListener: AddressSearchDetailClickListener) {
        itemClickListener = addressSearchClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        addressItems.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(address: List<AddressSearchResultDocumentResponse>) {
        addressItems.addAll(address)
        notifyDataSetChanged()
    }

    class AddressSearchDetailResultViewHolder(
        private val binding: ItemAddressSearchDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: AddressSearchResultDocumentResponse) {

            binding.lAddressResult.apply {
                when(item.addressType) {
                    // 도로명
                    "ROAD" -> {
                        tvAddressType.text = "도로명"
                        tvSearchKeyword.text = item.addressName
                        tvAddress.text = item.roadAddress?.addressName
                    }
                    // 도로명 + 건물번호
                    "ROAD_ADDR" -> {
                        tvAddressType.text = "도로명"
                        tvSearchKeyword.text = item.addressName
                        tvAddress.text = item.roadAddress?.addressName
                    }
                    // 지번주소
                    "REGION_ADDR" -> {
                        tvAddressType.text = "도로명"
                        tvSearchKeyword.text = item.address!!.addressName
                        tvAddress.text = item.roadAddress?.addressName
                    }

                    // 지역 이름
                    "REGION" -> {

                    }
                }
            }
        }
    }
}