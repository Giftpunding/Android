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
import com.giftfunding.osds.ui.address.adapter.AddressSearchAdapter3
import com.giftfunding.osds.ui.address.adapter.AddressSearchDetailAdapter

//삭제 예정
class AddressSearchActivity :
    BaseActivity<ActivityAddressSearchBinding>(ActivityAddressSearchBinding::inflate) {

    private val viewModel: AddressSearchViewModel by viewModels()
    private val addressSearchAdapter3 = AddressSearchAdapter3()
    private val addressSearchDetailAdapter = AddressSearchDetailAdapter()
    private val itemClickListener = object : AddressSearchClickListener {
        override fun addressSearchClickable(addressName: AddressSearchResultDocumentResponse) {
//            getAddress(addressName)
        }
    }

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
        addressSearchAdapter3.setItemClickListener(itemClickListener)
        binding.viewAddressSearchResult.rvAddressSearchResult.apply {
            adapter = addressSearchAdapter3
            layoutManager =
                LinearLayoutManager(this@AddressSearchActivity, LinearLayoutManager.VERTICAL, false)
        }

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
//                getAddress(address)
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
//    private fun getAddress(address: CharSequence?) {
//        viewModel.getAddress(
//            "KakaoAK ${resources.getString(R.string.rest_api_key)}",
//            address.toString()
//        )
//    }
//
//    //좌표값으로 주소지( 지번 또는 도로명) 가져오기
//    private fun getAddress(addressData: AddressSearchResultDocumentResponse) {
//        viewModel.getAddress(
//            "KakaoAK ${resources.getString(R.string.rest_api_key)}",
//            addressData
//        )
//    }

    //응답 된 데이터 안에 주소 데이터의 존재 여부에 따라 뷰 업데이트
    private fun updateAddressSearchView(address: AddressSearchResultResponse) {
        binding.viewAddressSearchGuide.root.visibility = View.GONE
        //데이터가 없을 경우 -> 결과물이 없다는 표시의 이미지를 표출
        if(address.documents?.isEmpty() == true){
            showAddressSearchNoResultView()
        }else{
            showAddressSearchResultView()
            // 검색 결과 보여주기 위해서 리사이클러 뷰 어뎁터에 데이터 넣어 줌
            addressSearchAdapter3.clearItems()
            addressSearchAdapter3.setKeyword(binding.editAddressSearch.text.toString())
            address.documents?.let { addressSearchAdapter3.addItems(it) }
        }
    }

    private fun showAddressSearchNoResultView(){
        binding.viewAddressSearchNoResult.root.visibility = View.VISIBLE
        binding.viewAddressSearchResult.root.visibility = View.GONE
        binding.viewAddressSearchDetailResult.root.visibility = View.GONE
    }

    private fun showAddressSearchResultView(){
        binding.viewAddressSearchNoResult.root.visibility = View.GONE
        binding.viewAddressSearchResult.root.visibility = View.VISIBLE
        binding.viewAddressSearchDetailResult.root.visibility = View.GONE
    }

    private fun updateAddressSearchDetailView(address: AddressSearchResultResponse) {
        //상세 주소 보기 위한 뷰
        showAddressSearchDetailResultView()
        addressSearchDetailAdapter.clearItems()
        address.documents?.let { addressSearchDetailAdapter.addItems(it) }
    }

    private fun showAddressSearchDetailResultView(){
        binding.viewAddressSearchNoResult.root.visibility = View.GONE
        binding.viewAddressSearchResult.root.visibility = View.GONE
        binding.viewAddressSearchDetailResult.root.visibility = View.VISIBLE
    }

    private fun initLiveData() {
        // 첫번째 주소 검색 결과
//        viewModel.isExistAddress.observe(this) { address ->
//            updateAddressSearchView(address)
//        }

        // 지번, 도로명 검색 결과
//        viewModel.isDetailAddress.observe(this){ address ->
//            updateAddressSearchDetailView(address)
//        }
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