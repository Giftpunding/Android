package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.databinding.ItemAddressSearchBinding
import com.giftpunding.osds.ui.address.ItemClickListener

class AddressSearchAdapter :
    RecyclerView.Adapter<AddressSearchAdapter.AddressSearchResultViewHolder>() {

    private var addressItems = ArrayList<AddressSearchResultDocumentResponse>()
    private var flag = false
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var addressKeyword: String

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
        if(flag){
            holder.onBind(addressItems[position], flag, addressKeyword)
            holder.itemView.setOnClickListener{
                flag = false
                addressItems[position].addressName = addressKeyword
                itemClickListener.clickDetailAddressName(addressItems[position])
            }
        }else{
            // 맨 처음 리사이클러 뷰를 생성할대는 검색어 영역이 필요없어 null 처리
            holder.onBind(addressItems[position], flag, null)
            holder.itemView.setOnClickListener{
                flag = true
                itemClickListener.clickAddressName(addressItems[position])
            }
        }
    }

    override fun getItemCount(): Int = addressItems.size

    // 인터페이스 주입
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    // 검색어 주입
    fun setAddressKeyword(keyword: String){
        addressKeyword = keyword
    }

    // 초기 상태, 주소 입력상태 관리
    fun setAddressFlag(flag: Boolean){
        this.flag = flag;
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(address: List<AddressSearchResultDocumentResponse>?) {
        if (address != null) {
            addressItems.addAll(address)
        }
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
            flag: Boolean,
            addressKeyword: String?,
        ) {
            if (flag) {
                binding.lAddressResult.root.visibility = View.VISIBLE
                binding.lAddressName.root.visibility = View.GONE

                binding.lAddressResult.apply {
                    tvSearchKeyword.text = addressKeyword
                    if (item.address != null) {
                        tvAddressType.text = "지번"
                        tvAddress.text = item.address!!.addressName
                    } else if (item.roadAddress != null) {
                        tvAddressType.text = "도로명"
                        tvAddress.text = item.roadAddress!!.roadName
                    }
                }

            } else {
                binding.lAddressResult.root.visibility = View.GONE
                binding.lAddressName.root.visibility = View.VISIBLE

                binding.lAddressName.tvAddress.text = item.addressName
            }
        }
    }
}

