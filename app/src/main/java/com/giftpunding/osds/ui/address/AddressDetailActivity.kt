package com.giftpunding.osds.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.content.res.AppCompatResources
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressDetailBinding

class AddressDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setEvent()
    }

    private fun init() {
        val addressData = intent.getSerializableExtra("AddressData") as AddressSearchResultResponse
        binding.tvSearchKeyword.text = addressData.searchKeyword
        binding.tvAddress.text = addressData.address

        binding.editAddressDetail.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editAddressDetail, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setEvent() {
        textChangeListener()

        binding.btnComplete.setOnClickListener {
            // TODO 상세주소 입력 완료
        }

        binding.btnTextDelete.setOnClickListener {
            binding.editAddressDetail.text = null
        }
    }

    private fun textChangeListener() {
        binding.apply {
            editAddressDetail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        btnComplete.isEnabled = false
                        btnTextDelete.visibility = View.GONE
                        editAddressDetail.background = AppCompatResources.getDrawable(
                            this@AddressDetailActivity,
                            R.drawable.bg_rect_midnight_express_solitude2_radius16_stroke2
                        )

                    } else {
                        btnComplete.isEnabled = true
                        btnTextDelete.visibility = View.VISIBLE
                        editAddressDetail.background = AppCompatResources.getDrawable(
                            this@AddressDetailActivity,
                            R.drawable.bg_rect_midnight_express_white_radius16_stroke2
                        )
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }
}