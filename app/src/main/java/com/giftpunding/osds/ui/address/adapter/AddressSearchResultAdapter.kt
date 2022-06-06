package com.giftpunding.osds.ui.address.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ItemAddressSearchResultBinding
import com.giftpunding.osds.ui.address.AddressDetailActivity

class AddressSearchResultAdapter(
    private val context: Context,
    private val addressSearchResultResponseList: List<AddressSearchResultResponse>
) :
    RecyclerView.Adapter<AddressSearchResultAdapter.AddressSearchResultViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressSearchResultViewHolder =
        AddressSearchResultViewHolder(
            ItemAddressSearchResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AddressSearchResultViewHolder, position: Int) {
        holder.onBind(addressSearchResultResponseList[position])
        holder.itemView.setOnClickListener {
            val addressIntent = Intent(context, AddressDetailActivity::class.java)
            addressIntent.putExtra("AddressData", addressSearchResultResponseList[position])
            context.startActivity(addressIntent)
        }
    }

    override fun getItemCount(): Int = addressSearchResultResponseList.size

    class AddressSearchResultViewHolder(private val binding: ItemAddressSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: AddressSearchResultResponse) {
            binding.apply {
                tvSearchKeyword.text = item.searchKeyword
                tvAddressType.text = item.addressType
                tvAddress.text = item.address
            }
        }
    }
}

