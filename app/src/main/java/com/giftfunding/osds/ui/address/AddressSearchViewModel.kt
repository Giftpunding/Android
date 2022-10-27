package com.giftfunding.osds.ui.address

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.data.response.address.AddressSearchResultResponse
import com.giftfunding.osds.data.response.user.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AddressSearchViewModel : ViewModel() {
    private val tag: String = "AddressSearchViewModel"

    private val _isExistAddress = MutableLiveData<AddressSearchResultResponse>()
    val isExistAddress: LiveData<AddressSearchResultResponse>
        get() = _isExistAddress

    private val _addressErrorMessage = MutableLiveData<String>()
    val addressErrorMessage: LiveData<String>
        get() = _addressErrorMessage

    private val _isUserInputText = MutableLiveData<Boolean>()
    val isUserInputText : LiveData<Boolean>
        get() = _isUserInputText

    // 주소 검색
    fun getAddress(apiKey: String, keyword: String, page: Int) {
        viewModelScope.launch(exceptionHandler) {
            _isExistAddress.value = Application.addressRepository.getAddress(apiKey, keyword, page)
        }
    }

    fun afterTextChange(text: String) {
        _isUserInputText.value = text.isNotEmpty()
    }

    // 코루틴 예외처리
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _addressErrorMessage.value = exception.message.toString()
        Log.d(tag, exception.message.toString())
    }
}