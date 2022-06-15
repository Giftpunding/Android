package com.giftpunding.osds.ui.address

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressSearchBinding
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState
import com.giftpunding.osds.ui.address.adapter.AddressSearchResultAdapter

class AddressSearchActivity :
    BaseActivity<ActivityAddressSearchBinding>(ActivityAddressSearchBinding::inflate) {

    // 더미데이터
    private val mList = arrayListOf(
        AddressSearchResultResponse("삼성아파트", "도로명", "삼성동 123-4"),
        AddressSearchResultResponse("현대아파트", "지번", "현대동 12-17")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()

    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.INVISIBLE)
        setTitle(getString(R.string.title_address_search))
        setCloseButton(VisibleState.VISIBLE)

        binding.editAddressSearch.requestFocus()
        revealKeyboard(binding.editAddressSearch)
    }

    override fun initEvent() {
        binding.btnTextDelete.setOnClickListener {
            binding.editAddressSearch.text = null
        }

        closeButton.setOnClickListener { finish() }

        binding.viewKeyboardHideTouchArea.setOnClickListener {
            hideKeyboard(binding.editAddressSearch)
        }

        searchButtonEvent()
        textChangeListener()
    }

    private fun searchButtonEvent() {
        binding.editAddressSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                // TODO 주소 검색 API 호출

                binding.viewAddressSearchGuide.root.visibility = View.GONE
                binding.viewAddressSearchResult.root.visibility = View.VISIBLE
            }
            false
        }

        binding.viewAddressSearchResult.rvAddressSearchResult.apply {
            adapter = AddressSearchResultAdapter(this@AddressSearchActivity, mList)
            layoutManager =
                LinearLayoutManager(this@AddressSearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun textChangeListener() {
        binding.apply {
            editAddressSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        btnTextDelete.visibility = View.GONE
                        editAddressSearch.background = AppCompatResources.getDrawable(
                            this@AddressSearchActivity,
                            R.drawable.bg_rect_midnight_express_solitude2_radius16_stroke2
                        )
                        editAddressSearch.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_search,
                            0,
                            0,
                            0
                        )
                    } else {
                        btnTextDelete.visibility = View.VISIBLE
                        editAddressSearch.background = AppCompatResources.getDrawable(
                            this@AddressSearchActivity,
                            R.drawable.bg_rect_midnight_express_white_radius16_stroke2
                        )
                        editAddressSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    /**
     * AddressDetailActivity에서 close 버튼 이벤트 발생시
     * 현재 액티비티를 종료하고 AddressActivity로 이동
     */
    private val startActivityForResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                finish()
            }
        }

    fun useActivityResultLauncher(intent: Intent) {
        startActivityForResult.launch(intent)
    }
}