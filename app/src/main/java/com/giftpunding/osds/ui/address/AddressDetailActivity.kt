package com.giftpunding.osds.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressDetailBinding

class AddressDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init() {
        val addressData = intent.getSerializableExtra("AddressData") as AddressSearchResultResponse
        binding.tvSearchKeyword.text = addressData.searchKeyword
        binding.tvAddress.text = addressData.address
    }

}