package com.giftfunding.osds.ui.address

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftfunding.osds.databinding.ActivityAddressDetailBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState
import com.giftfunding.osds.ui.home.HomeActivity


class AddressDetailActivity :
    BaseActivity<ActivityAddressDetailBinding>(ActivityAddressDetailBinding::inflate) {

    private val viewModel : AddressSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()
        initAddressViewModel()
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.VISIBLE)
        setBackButton(BackButton.ARROW_BACK)
        setTitle(getString(R.string.title_address_detail))
        setCloseButton(VisibleState.VISIBLE)

        val addressData = intent.getSerializableExtra("AddressData") as AddressSearchResultDocumentResponse

        binding.tvSearchKeyword.text = addressData.addressName
        
        //도로명, 지번 구분 표시
        initAddressTextViews(addressData)

        binding.editAddressDetail.requestFocus()
        revealKeyboard(binding.editAddressDetail)
    }

    private fun initAddressViewModel(){
        viewModel.userResponse.observe(this){
            finishAffinity()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    private fun initAddressTextViews(addressData: AddressSearchResultDocumentResponse) {
       // 도로명이 있는 경우
        if (addressData.roadAddress != null) {
            updateRoadAddressTypeView(addressData)
        }
        // 도로명은 없고 지번이 있는 경우
        else if(addressData.address != null){
            updateAddressTypeView(addressData)
        }
    }

    //도로명으로 보여주기
    @SuppressLint("SetTextI18n")
    private fun updateRoadAddressTypeView(addressData: AddressSearchResultDocumentResponse) {
        binding.tvAddressType.text = "도로명"
        binding.tvAddress.text = addressData.roadAddress!!.roadName
        if(isExistBuildingName(addressData)){
            binding.tvSearchKeyword.text =
                addressData.roadAddress!!.addressName + " " + addressData.roadAddress!!.buildingName
        }else{
            binding.tvSearchKeyword.text = addressData.roadAddress!!.addressName
        }
    }

    // 도로명에서 건물 이름이 있는 경우
    private fun isExistBuildingName(addressData: AddressSearchResultDocumentResponse): Boolean{
        return addressData.roadAddress!!.buildingName != ""
    }

    // 지번으로 보여주기
    private fun updateAddressTypeView(addressData: AddressSearchResultDocumentResponse) {
        binding.tvSearchKeyword.text = addressData.address!!.addressName
        binding.tvAddressType.text = "지번"
        binding.tvAddress.text = addressData.address!!.addressName
    }

    override fun initEvent() {
        binding.btnComplete.setOnClickListener {
            // TODO 회원가입 완료(주소 저장) API 호출
            val address = "${binding.tvAddress.text} ${binding.editAddressDetail.text}"
            viewModel.addAddress(address)
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
        keypadUpDownListener()
    }

    private fun keypadUpDownListener(){
        binding.editAddressDetail.viewTreeObserver.addOnGlobalLayoutListener{
            val r = Rect()
            binding.editAddressDetail.getWindowVisibleDisplayFrame(r)
            //정확한 수치가 없음
            if(binding.editAddressDetail.rootView.height - (r.bottom - r.top) > 600){
                Log.e("keypad", "on")
            }
            else{
                Log.d("keypad","off")
            }
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