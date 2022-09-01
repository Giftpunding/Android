package com.giftpunding.osds.ui.address

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import com.giftpunding.osds.databinding.ActivityAddressSearchBinding
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState
import com.giftpunding.osds.ui.address.adapter.AddressSearchAdapter

class AddressSearchActivity :
    BaseActivity<ActivityAddressSearchBinding>(ActivityAddressSearchBinding::inflate) {

    private val viewModel: AddressSearchViewModel by viewModels()
    private val addressSearchAdapter = AddressSearchAdapter()
    private val itemClickListener = object : ItemClickListener {
        override fun clickAddressName(addressName: AddressSearchResultDocumentResponse) {
            getAddress(addressName)
            addressSearchAdapter.setAddressKeyword(addressName.addressName!!)
        }

        override fun clickDetailAddressName(addressResult: AddressSearchResultDocumentResponse) {
            val addressIntent = Intent(this@AddressSearchActivity, AddressDetailActivity::class.java)
            addressIntent.putExtra("AddressData", addressResult)
            useActivityResultLauncher(addressIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initRecyclerView()
        initEvent()
        initLiveData()

    }

    override fun init() {
        initToolbar()
        binding.editAddressSearch.requestFocus()
        revealKeyboard(binding.editAddressSearch)
    }

    private fun initRecyclerView() {
        addressSearchAdapter.setItemClickListener(itemClickListener)
        binding.viewAddressSearchResult.rvAddressSearchResult.apply {
            adapter = addressSearchAdapter
            layoutManager =
                LinearLayoutManager(this@AddressSearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initToolbar() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.INVISIBLE)
        setTitle(getString(R.string.title_address_search))
        setCloseButton(VisibleState.VISIBLE)
    }

    override fun initEvent() {
        // 주소 검색 삭제 버튼
        binding.btnTextDelete.setOnClickListener {
            binding.editAddressSearch.text = null

            //Guide 제외 나머지로 돌아가기
            binding.viewAddressSearchNoResult.root.visibility = View.GONE
            binding.viewAddressSearchDetailResult.root.visibility = View.GONE
            binding.viewAddressSearchResult.root.visibility = View.GONE
            binding.viewAddressSearchGuide.root.visibility = View.VISIBLE

            addressSearchAdapter.setAddressFlag(false);
        }

        // 주소 입력 화면 종료 버튼
        closeButton.setOnClickListener {
            finish()
        }

        // 주소 검색 완료 버튼
        // 해당 버튼은 키보드에 있음
        binding.editAddressSearch.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                binding.viewAddressSearchGuide.root.visibility = View.GONE
                binding.viewAddressSearchResult.root.visibility = View.VISIBLE
                hideKeyboard(binding.editAddressSearch)
            }
            false
        }

        addSearchTextWatcher()
    }

    private fun addSearchTextWatcher() {
        binding.editAddressSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(
                address: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                updateEditAddressSearchBackground(address)
                isShowAddressDeleteButton(address)
                getAddress(address)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun isShowAddressDeleteButton(address: CharSequence?) {
        if (address.isNullOrEmpty()) {
            binding.btnTextDelete.visibility = View.GONE
        } else {
            binding.btnTextDelete.visibility = View.VISIBLE
        }
    }

    private fun updateEditAddressSearchBackground(address: CharSequence?) {
        if (address.isNullOrEmpty()) {
            binding.editAddressSearch.apply {
                background = AppCompatResources.getDrawable(
                    this@AddressSearchActivity,
                    R.drawable.bg_rect_midnight_express_solitude2_radius16_stroke2
                )

                setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_search,
                    0,
                    0,
                    0
                )
            }
        } else {
            binding.editAddressSearch.apply {
                background = AppCompatResources.getDrawable(
                    this@AddressSearchActivity,
                    R.drawable.bg_rect_midnight_express_white_radius16_stroke2
                )
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    0,
                    0
                )
            }
        }
    }

    // 주소 검색
    private fun getAddress(address: CharSequence?) {
        viewModel.getAddress(
            "KakaoAK ${resources.getString(R.string.kakao_rest_api_key)}",
            address.toString()
        )
    }

    //좌표값으로 주소지( 지번 또는 도로명) 가져오기
    private fun getAddress(addressData: AddressSearchResultDocumentResponse) {
        viewModel.getAddress(
            "KakaoAK ${resources.getString(R.string.kakao_rest_api_key)}",
            addressData
        )
    }

    //응답 된 데이터 안에 주소 데이터의 존재 여부에 따라 뷰 업데이트
    private fun updateAddressView(address: AddressSearchResultResponse) {
        if (address.documents?.isEmpty() == true) {
            binding.viewAddressSearchGuide.root.visibility = View.VISIBLE
            binding.viewAddressSearchResult.root.visibility = View.GONE
        } else {
            binding.viewAddressSearchGuide.root.visibility = View.GONE
            binding.viewAddressSearchResult.root.visibility = View.VISIBLE
        }

        // 검색 결과 보여주기 위해서 리사이클러 뷰 어뎁터에 데이터 넣어 줌
        addressSearchAdapter.clearItems()
        addressSearchAdapter.addItems(address.documents)
    }


    private fun initLiveData() {
        viewModel.isExistAddress.observe(this) { address ->
            updateAddressView(address)
        }

        viewModel.detailAddressName.observe(this){ address ->
            updateAddressView(address)
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