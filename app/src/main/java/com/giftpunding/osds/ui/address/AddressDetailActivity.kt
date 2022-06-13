package com.giftpunding.osds.ui.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressDetailBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

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

        // 이전 액티비티에서 넘겨받은 주소 설정
        val addressData = intent.getSerializableExtra("AddressData") as AddressSearchResultResponse
        binding.tvSearchKeyword.text = addressData.searchKeyword
        binding.tvAddress.text = addressData.address

        // 자동으로 키보드 띄우기
        binding.editAddressDetail.requestFocus()
        revealKeyboard(binding.editAddressDetail)
    }

    override fun initEvent() {
        textChangeListener()

        binding.btnComplete.setOnClickListener {
            // TODO 상세주소 입력 완료
        }

        binding.btnTextDelete.setOnClickListener {
            binding.editAddressDetail.text = null
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus!!)
        return super.dispatchTouchEvent(ev)
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