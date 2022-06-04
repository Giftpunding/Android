package com.giftpunding.osds.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressSearchBinding
import com.giftpunding.osds.ui.address.adapter.AddressSearchResultAdapter

class AddressSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressSearchBinding

    // 더미데이터
    private val mList = arrayListOf(
        AddressSearchResultResponse("우성아파트", "도로명", "우성123-4"),
        AddressSearchResultResponse("현광아파트", "지번", "현광동 12-17")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBindView()
    }

    private fun onBindView() {
        binding.viewAddressSearchResult.rvAddressSearchResult.apply {
            adapter = AddressSearchResultAdapter(mList)
            layoutManager =
                LinearLayoutManager(this@AddressSearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}