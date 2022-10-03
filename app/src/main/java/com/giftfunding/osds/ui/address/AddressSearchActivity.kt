package com.giftfunding.osds.ui.address

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
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftfunding.osds.data.response.address.AddressSearchResultResponse
import com.giftfunding.osds.databinding.ActivityAddressSearchBinding
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState
import com.giftfunding.osds.ui.address.adapter.AddressSearchDetailAdapter

class AddressSearchActivity :
    BaseActivity<ActivityAddressSearchBinding>(ActivityAddressSearchBinding::inflate) {

    private val viewModel: AddressSearchViewModel by viewModels()
    private val addressSearchDetailAdapter = AddressSearchDetailAdapter()

    private val addressSearchClickListener = object : AddressSearchDetailClickListener{
        override fun addressSearchDetailClickable(addressResult: AddressSearchResultDocumentResponse) {
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
        // 주소 검색
        addressSearchDetailAdapter.setClickListener(addressSearchClickListener)
        binding.viewAddressSearchDetailResult.rvAddressSearchResult.apply {
            adapter = addressSearchDetailAdapter
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
        }

        // 주소 입력 화면 종료 버튼
        closeButton.setOnClickListener {
            finish()
        }

        // 주소 검색 완료 버튼
        // 해당 버튼은 키보드에 있음
        binding.editAddressSearch.setOnEditorActionListener { textView, i, _ ->
            var handled = false

            if (i == EditorInfo.IME_ACTION_SEARCH) {
                binding.viewAddressSearchGuide.root.visibility = View.GONE
                binding.viewAddressSearchResult.root.visibility = View.VISIBLE
                getAddress(binding.editAddressSearch.text.toString())
                handled = true
            }
            handled
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
            "KakaoAK ${resources.getString(R.string.rest_api_key)}",
            address.toString()
        )
    }

    private fun showAddressSearchNoResultView(){
        binding.viewAddressSearchNoResult.root.visibility = View.VISIBLE
        binding.viewAddressSearchResult.root.visibility = View.GONE
        binding.viewAddressSearchDetailResult.root.visibility = View.GONE
    }

    private fun updateAddressSearchDetailView(address: AddressSearchResultResponse) {
        //상세 주소 보기 위한 뷰
        if(address.documents?.isEmpty() == true || address.documents?.get(0)!!.addressType == "REGION"){
            showAddressSearchNoResultView()
        }else{
            showAddressSearchDetailResultView()
            addressSearchDetailAdapter.clearItems()
            address.documents?.let { addressSearchDetailAdapter.addItems(it) }
        }
    }

    private fun showAddressSearchDetailResultView(){
        binding.viewAddressSearchNoResult.root.visibility = View.GONE
        binding.viewAddressSearchResult.root.visibility = View.GONE
        binding.viewAddressSearchDetailResult.root.visibility = View.VISIBLE
    }

    private fun initLiveData() {
        viewModel.isExistAddress.observe(this) { address ->
            updateAddressSearchDetailView(address)
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