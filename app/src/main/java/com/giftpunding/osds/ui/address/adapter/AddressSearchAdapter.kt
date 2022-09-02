package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.Log
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

    fun getFirstAddressView() : Boolean{
        return isFirstAddressView
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
                initAddressResultView(item)
            }
        }

        private fun initAddressNameView(
            item: AddressSearchResultDocumentResponse,
            keyword: String
        ) {
            //indexof로 첫글자 찾기, 그 후 키워드의 숫자 만큼 글자색 변경
            //item = 주소 정보가 담긴 객체
            //keyword = 내가 입력한 글씨

            Log.d("keyword test", "${item.addressName?.indexOf(keyword)}")

            val searchIdx = item.addressName?.indexOf(keyword)!!
            val currentKeywordSize = keyword.length

            binding.lAddressName.tvAddress.text = item.addressName
            val span = binding.lAddressName.tvAddress.text as android.text.SpannableString
            span.setSpan(ForegroundColorSpan(Color.BLUE),searchIdx,searchIdx+currentKeywordSize, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.lAddressName.tvAddress.text = span
        }

        private fun initAddressResultView(
            item: AddressSearchResultDocumentResponse,
        ) {
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

