package com.giftfunding.osds.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.domain.base.dto.UnitDto
import kotlinx.coroutines.launch

class AddressDetailViewModel : ViewModel() {

    var keyboardHeight: Int = 0
        private set

    private val _isVisibleDeleteButton = MutableLiveData<Boolean>()
    val isVisibleDeleteButton: LiveData<Boolean> get() = _isVisibleDeleteButton

    private val _isExistText = MutableLiveData<Boolean>()
    val isExistText: LiveData<Boolean> get() = _isExistText

    private val _isEnableButton = MutableLiveData<Boolean>()
    val isEnableButton: LiveData<Boolean> get() = _isEnableButton

    private val _addAddressResponse = MutableLiveData<ViewState<UnitDto>>()
    val addAddressResponse: LiveData<ViewState<UnitDto>> get() = _addAddressResponse

    fun setKeyboardHeight(keyboardHeight: Int) {
        this.keyboardHeight = keyboardHeight
    }

    fun addressDetailTextChanged(detailAddress: String) {
        if (detailAddress.isEmpty()) {
            _isVisibleDeleteButton.value = false
            _isExistText.value = false
            _isEnableButton.value = false
            return
        }

        _isVisibleDeleteButton.value = true
        _isExistText.value = true
        _isEnableButton.value = true
    }

    fun addUserAddress(value: String, detail: String, memo: String) = viewModelScope.launch {
        _addAddressResponse.value = ViewState.Loading()
        try {
            val response = Application.addressUseCase.addUserAddress(value, detail, memo)
            _addAddressResponse.value = ViewState.Success(response)
        } catch (e: Exception) {
            _addAddressResponse.value = ViewState.Error(e.message)
        }
    }

}