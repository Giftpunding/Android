package com.giftpunding.osds.ui.address.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.databinding.ItemAddressSearchBinding
import com.giftpunding.osds.databinding.ItemAddressSearchResultBinding
import com.giftpunding.osds.ui.address.AddressDetailActivity
import com.giftpunding.osds.ui.address.AddressSearchActivity

class AddressSearchDetailAdapter(
    private val context: Context,
    private val addressSearchResultResponseList: List<AddressSearchResultDocumentResponse>
) :
    RecyclerView.Adapter<AddressSearchDetailAdapter.AddressSearchDetailViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressSearchDetailViewHolder =
        AddressSearchDetailViewHolder(
            ItemAddressSearchResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AddressSearchDetailViewHolder, position: Int) {
        val addressIntent = Intent(context, AddressDetailActivity::class.java)
        addressIntent.putExtra("AddressData", addressSearchResultResponseList[position])

        holder.onBind(addressSearchResultResponseList[position])
        holder.itemView.setOnClickListener {
            (context as AddressSearchActivity).useActivityResultLauncher(addressIntent)
        }
    }

    override fun getItemCount(): Int = addressSearchResultResponseList.size

    class AddressSearchDetailViewHolder(private val binding: ItemAddressSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: AddressSearchResultDocumentResponse) {
            binding.apply {
                tvAddress.text = item.addressName
            }
        }
    }
}

