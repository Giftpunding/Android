package com.giftfunding.osds.ui.address

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.data.response.address.AddressSearchResultDocumentResponse
import com.giftfunding.osds.data.response.address.AddressSearchResultResponse
import com.giftfunding.osds.data.response.user.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AddressSearchViewModel : ViewModel() {
    private val tag: String = "AddressSearchViewModel"

    private val _isExistAddress = MutableLiveData<AddressSearchResultResponse>()
    val isExistAddress: LiveData<AddressSearchResultResponse>
        get() = _isExistAddress

    private val _isExistDetailAddress = MutableLiveData<AddressSearchResultResponse>()
    val isDetailAddress: LiveData<AddressSearchResultResponse>
        get() = _isExistDetailAddress

    private val _userResponse = MutableLiveData<User>()
    val userResponse: LiveData<User>
        get() = _userResponse

    fun addAddress(address : String){
        viewModelScope.launch(exceptionHandler){
            val response = Application.addressRepository.addAddress(address)
            if(response.isSuccessful){
                _userResponse.value = response.body()
            }
            else {
                Log.e("AddressSearchViewModel", "err")
            }
        }
    }

    fun getAddress(apiKey: String, keyword: String) {
        viewModelScope.launch(exceptionHandler) {
            val result = Application.addressRepository.getAddress(apiKey, keyword)
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
            _isExistDetailAddress.value = result
        }
    }

    // 코루틴 예외처리
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(tag, exception.message.toString())
    }
}