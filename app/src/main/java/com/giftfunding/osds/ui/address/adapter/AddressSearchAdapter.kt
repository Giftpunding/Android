package com.giftfunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftfunding.osds.databinding.ItemAddressSearchBinding

class AddressSearchAdapter :
    RecyclerView.Adapter<AddressSearchAdapter.AddressSearchViewHolder>() {

    private val addressItems = mutableListOf<AddressSearchResultDocumentResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AddressSearchViewHolder =
        AddressSearchViewHolder(
            ItemAddressSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AddressSearchViewHolder, position: Int) {
        holder.onBind(addressItems[position])
    }

    override fun getItemCount(): Int = addressItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(address: List<AddressSearchResultDocumentResponse>) {
        Log.d("addItems", " TEST ")
        addressItems.addAll(address)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        addressItems.clear()
        notifyDataSetChanged()
    }

    class AddressSearchViewHolder(private val binding: ItemAddressSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: AddressSearchResultDocumentResponse) {

            when (item.addressType) {
                "REGION" -> {
                    binding.lAddressResult.tvSearchKeyword.text = item.addressName
                    binding.lAddressResult.tvAddress.text = item.address!!.addressName
                    binding.lAddressResult.tvAddressType.text = "지번"
                }

                "REGION_ADDR" -> {
                    binding.lAddressResult.tvSearchKeyword.text = item.addressName
                    binding.lAddressResult.tvAddress.text = item.address!!.addressName
                    binding.lAddressResult.tvAddressType.text = "지번"
                }

                "ROAD" -> {
                    binding.lAddressResult.tvSearchKeyword.text = item.addressName
                    binding.lAddressResult.tvAddress.text = item.roadAddress!!.addressName
                    binding.lAddressResult.tvAddressType.text = "도로명"
                }

                "ROAD_ADDR" -> {
                    isExistBuildingName(item)
                    binding.lAddressResult.tvAddress.text = item.roadAddress!!.addressName
                    binding.lAddressResult.tvAddressType.text = "도로명"
                }
            }

        }

        // 빌딩 이름이 존재 하면 빌딩이름으로 보여주기
        private fun isExistBuildingName(item: AddressSearchResultDocumentResponse) {
            if (item.roadAddress!!.buildingName.isNullOrEmpty()) {
                binding.lAddressResult.tvSearchKeyword.text = item.addressName
            } else {
                binding.lAddressResult.tvSearchKeyword.text = item.roadAddress!!.buildingName
            }
        }

    }
}