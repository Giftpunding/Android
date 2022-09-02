package com.giftpunding.osds.ui.address.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
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
import java.util.*
import kotlin.collections.ArrayList

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

        if (isFirstAddressView) {
            holder.itemView.setOnClickListener {
                isFirstAddressView = false
                itemClickListener.clickAddressName(addressItems[position])
            }
        } else {
            holder.itemView.setOnClickListener {
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
    fun setAddressKeyword(keyword: String) {
        addressKeyword = keyword
    }

    fun getFirstAddressView(): Boolean {
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
            binding.lAddressName.tvAddress.text = ""
            val st = StringTokenizer(item.addressName)

            while (st.hasMoreTokens()) {
                //경기 용인시 기흥구 신갈로58번길
                val token = st.nextToken()
                //keyword : 신갈로, token : 신갈로58번길
                Log.d("current token" ,token)
                if (keyword.contains(token)) {
                    Log.d("keyword is contain",token)
                    val ssb = SpannableStringBuilder("$token ")
                    //무조건 포함할까? 경기, 경기도면? 신갈로58번길, 신갈로면?
                    ssb.apply{
                        //토큰 기반 -> 신갈로58번길, 신갈로
                        if(token.length > keyword.length){
                            setSpan(ForegroundColorSpan(Color.BLUE), 0, keyword.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                        //키워드 기반 -> 경기, 경기도
                        else{
                            setSpan(ForegroundColorSpan(Color.BLUE), 0, token.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    }
                    binding.lAddressName.tvAddress.append(ssb)
                }
            }
            binding.lAddressName.tvAddress.text = item.addressName
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
                    if (item.roadAddress?.buildingName == "") {
                        tvSearchKeyword.text = item.roadAddress?.addressName
                    } else {
                        tvSearchKeyword.text = item.roadAddress?.buildingName
                    }
                }
            }
        }

        private fun changeRecyclerView(isFirstAddressView: Boolean) {
            if (isFirstAddressView) {
                binding.lAddressResult.root.visibility = View.GONE
                binding.lAddressName.root.visibility = View.VISIBLE
            } else {
                binding.lAddressResult.root.visibility = View.VISIBLE
                binding.lAddressName.root.visibility = View.GONE
            }
        }
    }
}

