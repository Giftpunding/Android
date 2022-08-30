package com.giftpunding.osds.ui.address

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftpunding.osds.application.Application
import com.giftpunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftpunding.osds.data.response.address.AddressSearchResultResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AddressSearchViewModel : ViewModel() {
    private val tag: String = "AddressSearchViewModel"

    private val _isExistAddress = MutableLiveData<AddressSearchResultResponse>()
    val isExistAddress: LiveData<AddressSearchResultResponse>
        get() = _isExistAddress

    private val _detailAddressName = MutableLiveData<AddressSearchResultResponse>()
    val detailAddressName: LiveData<AddressSearchResultResponse>
        get() = _detailAddressName

    fun getAddress(apiKey: String, query: String, page: Int, size: Int) {
        viewModelScope.launch(exceptionHandler) {
            val result = Application.addressRepository.getAddress(apiKey, query, page, size)
            _isExistAddress.value = result
        }
    }

    //좌표값으로 주소지( 지번 또는 도로명) 가져오기
    fun getAddress(apiKey: String, addressData: AddressSearchResultDocumentResponse) {
        viewModelScope.launch(exceptionHandler) {
            val result = Application.addressRepository.getAddress(
                apiKey,
                addressData.x.toString(),
                addressData.y.toString()
            )
            _detailAddressName.value = result
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(tag, exception.message.toString())
    }
}