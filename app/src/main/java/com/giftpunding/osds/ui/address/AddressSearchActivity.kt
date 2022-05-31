package com.giftpunding.osds.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.giftpunding.osds.databinding.ActivityAddressSearchBinding

class AddressSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}