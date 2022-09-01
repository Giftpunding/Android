package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.databinding.ItemAddressSearchBinding
import com.giftpunding.osds.ui.address.ItemClickListener
import com.giftpunding.osds.util.SpannableString

class AddressSearchAdapter :
    RecyclerView.Adapter<AddressSearchAdapter.AddressSearchResultViewHolder>() {

    private var addressItems = ArrayList<AddressSearchResultDocumentResponse>()
    private var isFirstAddressView = true
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
        holder.onBind(addressItems[position], isFirstAddressView, addressKeyword)

        if(isFirstAddressView){
            holder.itemView.setOnClickListener{
                isFirstAddressView = false
                itemClickListener.clickAddressName(addressItems[position])
            }
        }else{
            holder.itemView.setOnClickListener{
                isFirstAddressView = true
                addressItems[position].addressName = addressKeyword
                itemClickListener.clickDetailAddressName(addressItems[position])
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
            isFirstAddressView: Boolean,
            keyword: String
        ) {
            changeRecyclerView(isFirstAddressView)

            if (isFirstAddressView) {
                //첫번째 주소 검색 보여주는 뷰
                initAddressNameView(item, keyword)
            } else {
                //첫번째 주소 검색 후 주소의 좌표값을 통해 지번과 도로명 보여주는 뷰
                initAddressResultView(item, keyword)
            }
        }

        private fun initAddressNameView(
            item: AddressSearchResultDocumentResponse,
            keyword: String
        ) {
            binding.lAddressName.tvAddress.text = (SpannableString.setTextColor(item.addressName!!, keyword))
        }

        private fun initAddressResultView(
            item: AddressSearchResultDocumentResponse,
            keyword: String
        ) {
            binding.lAddressResult.apply {
                tvSearchKeyword.text = keyword
                if (item.address != null) {
                    tvAddressType.text = "지번"
                    tvAddress.text = item.address!!.addressName
                } else if (item.roadAddress != null) {
                    tvAddressType.text = "도로명"
                    tvAddress.text = item.roadAddress!!.roadName
                }
            }
        }

        private fun changeRecyclerView(isFirstAddressView: Boolean) {
            if(isFirstAddressView){
                binding.lAddressResult.root.visibility = View.GONE
                binding.lAddressName.root.visibility = View.VISIBLE
            }else{
                binding.lAddressResult.root.visibility = View.VISIBLE
                binding.lAddressName.root.visibility = View.GONE
            }
        }
    }
}

