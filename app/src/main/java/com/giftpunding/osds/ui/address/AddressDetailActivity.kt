package com.giftpunding.osds.ui.address

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.data.response.address.AddressSearchResultResponseTemp
import com.giftpunding.osds.databinding.ActivityAddressDetailBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState
import com.giftpunding.osds.ui.home.HomeActivity


class AddressDetailActivity :
    BaseActivity<ActivityAddressDetailBinding>(ActivityAddressDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.VISIBLE)
        setBackButton(BackButton.ARROW_BACK)
        setTitle(getString(R.string.title_address_detail))
        setCloseButton(VisibleState.VISIBLE)

        val addressData = intent.getSerializableExtra("AddressData") as AddressSearchResultDocumentResponse

        binding.tvSearchKeyword.text = addressData.addressName
        
        //도로명, 지번 구분 표시
        if (addressData.address != null) {
            binding.tvAddressType.text = "지번"
            binding.tvAddress.text = addressData.address!!.addressName
        } else if (addressData.roadAddress != null) {
            binding.tvAddressType.text = "도로명"
            binding.tvAddress.text = addressData.roadAddress!!.roadName
        }

        binding.editAddressDetail.requestFocus()
        revealKeyboard(binding.editAddressDetail)
    }

    override fun initEvent() {
        binding.btnComplete.setOnClickListener {
            // TODO 회원가입 완료(주소 저장) API 호출
            finishAffinity()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.btnTextDelete.setOnClickListener {
            binding.editAddressDetail.text = null
        }

        backButton.setOnClickListener {
            finish()
        }

        closeButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.viewKeyboardHideTouchArea.setOnClickListener {
            hideKeyboard(binding.editAddressDetail)
        }

        textChangeListener()
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