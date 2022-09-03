package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.databinding.ItemAddressSearchDetailBinding
import com.giftpunding.osds.ui.address.AddressSearchDetailClickListener

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
                if (item.address != null) {
                    tvAddressType.text = "지번"
                    tvAddress.text = item.address!!.addressName
                    tvSearchKeyword.text = item.address!!.addressName
                }
                if (item.roadAddress != null) {
                    tvAddressType.text = "도로명"
                    tvAddress.text = item.roadAddress?.addressName
                    if(item.roadAddress?.buildingName == ""){
                        tvSearchKeyword.text = item.roadAddress?.addressName
                    }
                    else{
                        tvSearchKeyword.text = item.roadAddress?.buildingName
                    }
                }
            }
        }
    }
}