package com.giftfunding.osds.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddressDetailViewModel : ViewModel() {

    var keyboardHeight: Int = 0
        private set

    private val _isVisibleDeleteButton = MutableLiveData<Boolean>()
    val isVisibleDeleteButton: LiveData<Boolean>
        get() = _isVisibleDeleteButton

    private val _isExistText = MutableLiveData<Boolean>()
    val isExistText: LiveData<Boolean>
        get() = _isExistText

    private val _isEnableButton = MutableLiveData<Boolean>()
    val isEnableButton: LiveData<Boolean>
        get() = _isEnableButton

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

}