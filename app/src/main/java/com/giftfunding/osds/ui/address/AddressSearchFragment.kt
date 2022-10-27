package com.giftfunding.osds.ui.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.data.response.address.AddressSearchResultResponse
import com.giftfunding.osds.databinding.FragmentAddressSearchBinding
import com.giftfunding.osds.ui.address.adapter.AddressSearchAdapter
import com.giftfunding.osds.util.*

class AddressSearchFragment : BaseFragment<FragmentAddressSearchBinding>() {

    override fun layoutResId(): Int = R.layout.fragment_address_search

    private val viewModel: AddressSearchViewModel by viewModels()
    private val addressSearchAdapter = AddressSearchAdapter()
    private var page = DEFAULT_PAGE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initEvent()
        initRecyclerView()
        initObserverEvent()
    }

    private fun init() {
        // 화면으로 들어올 때 자동으로 editText 포커스 및 키보드 올리기
        binding.editAddressSearch.setFocusAndShowKeyboard(binding.editAddressSearch.context)
    }

    override fun initEvent() {
        searchAddress()
        editTextTextWatcher()
        binding.btnTextDelete.setOnClickListener {
            binding.editAddressSearch.text.clear()
        }
    }

    private fun initRecyclerView() {
        binding.viewAddressSearchResult.rvAddressSearchResult.apply {
            adapter = addressSearchAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun initObserverEvent() {
        //주소 검색 결과
        viewModel.isExistAddress.observe(viewLifecycleOwner) { address ->
            isExistAddressInformation(address)
        }

        // 주소 검색시 카카오 api 에서 주는 errorMessage ... 테스트 필요
        viewModel.addressErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            showSnackBar(errorMessage)
        }

        // editText에 텍스트가 입력되었을 떄, x 표시 보여주기
        viewModel.isUserInputText.observe(viewLifecycleOwner) { isExistText ->
            updateEditAddressSearchBackground(isExistText)
        }
    }

    // editText에 주소를 입력 후 검색 버튼 클릭
    private fun searchAddress() {
        binding.editAddressSearch.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(
                textView: TextView?,
                actionId: Int,
                keyEvent: KeyEvent?
            ): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        hideKeyboard()
                        isSearchAddress(binding.editAddressSearch.text.toString())
                    }
                    else -> // 기본 엔터키 동작
                        return false
                }
                return true
            }
        })
    }

    // editText에 주소지 검색했는지 확인하기
    private fun isSearchAddress(address: String) {
        if (address.isEmpty()) {
            showSnackBar(getString(R.string.content_empty_address))
            return
        }

        reSearchAddress()
    }

    // 재검색시 page 초기화, 주소 검색 내용 초기화
    private fun reSearchAddress() {
        initSearchConditions()
        getAddress()
    }

    // 재검색을 위한 초기화
    private fun initSearchConditions() {
        page = DEFAULT_PAGE
        addressSearchAdapter.clearItems()
    }

    //키워드로 주소 검색하기
    private fun getAddress() {
        viewModel.getAddress(
            "KakaoAK ${resources.getString(R.string.rest_api_key)}",
            binding.editAddressSearch.text.toString(),
            page
        )
    }

    // 검색 결과가 더 이상 없는지 확인하기
    private fun isEndPage(address: AddressSearchResultResponse): Boolean? = address.meta!!.isEnd

    //주소 검색 결과값이 있는지 없는지 판별
    private fun isExistAddressInformation(address: AddressSearchResultResponse) {
        if (address.documents.isNullOrEmpty()) {
            showAddressSearchNoResultView()
        } else {
            showAddressSearchResultView()
            addressSearchAdapter.addItems(address.documents!!)

            //끝 페이지가 아니라면 , 페이지 증가하고 주소지 검색 더 하기
            if (!isEndPage(address)!!) {
                page++
                getAddress()
            }
        }
    }

    // editText 텍스트 와쳐 리스너
    private fun editTextTextWatcher() {
        binding.editAddressSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                viewModel.afterTextChange(text.toString())
            }
        })
    }

    // edittext에 텍스트 존재 유무에 따라 뷰 갱신
    private fun updateEditAddressSearchBackground(isExistText: Boolean) {
        changeDeleteButtonVisibleState(isExistText)
        changeBackgroundColor(isExistText)
        changeDrawableStartVisibleState(isExistText)
    }

    // x 버튼 활성화 상태 변경
    private fun changeDeleteButtonVisibleState(isExistText: Boolean) {
        binding.btnTextDelete.visibility = if (isExistText) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    // edittext 백그라운드 색 변경
    private fun changeBackgroundColor(isExistText: Boolean) {
        if (isExistText) {
            binding.editAddressSearch.changeBackgroundColor(
                requireContext(),
                R.drawable.bg_rect_midnight_express_white_radius16_stroke2
            )
        } else {
            binding.editAddressSearch.changeBackgroundColor(
                requireContext(),
                R.drawable.bg_rect_midnight_express_solitude2_radius16_stroke2
            )
        }
    }

    // 검색 아이콘 표출 여부 변경
    private fun changeDrawableStartVisibleState(isExistText: Boolean) {
        if (isExistText) {
            binding.editAddressSearch.hideSearchIcon()
        } else {
            binding.editAddressSearch.showSearchIcon(R.drawable.ic_search)
        }
    }

    //검색 결과 없을 때 : 가이드, 결과 안보이게 하기
    private fun showAddressSearchNoResultView() {
        binding.viewAddressSearchGuide.root.visibility = View.GONE
        binding.viewAddressSearchNoResult.root.visibility = View.VISIBLE
        binding.viewAddressSearchResult.root.visibility = View.GONE
    }

    //검색 결과 존재 할 때 : 가이드, 결과 없음 안보이게 하기
    private fun showAddressSearchResultView() {
        binding.viewAddressSearchGuide.root.visibility = View.GONE
        binding.viewAddressSearchNoResult.root.visibility = View.GONE
        binding.viewAddressSearchResult.root.visibility = View.VISIBLE
    }

    //키보드 숨기기
    private fun hideKeyboard() {
        binding.editAddressSearch.clearFocusAndHideKeyboard(
            binding.editAddressSearch.context
        )
    }

    //스낵바로 에러 보여주기
    private fun showSnackBar(errorMessage: String) {
        Util.showSnackBar(requireView(), errorMessage)
    }

    companion object {
        const val DEFAULT_PAGE = 1
    }
}