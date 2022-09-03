package com.giftpunding.osds.ui.anniversary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftpunding.osds.application.Application
import com.giftpunding.osds.data.response.anniversary.AnniversaryResponse
import kotlinx.coroutines.launch

class AnniversaryFragmentViewModel : ViewModel() {

    private val _anniversaryResponse = MutableLiveData<AnniversaryResponse>()
    val anniversaryResponse: LiveData<AnniversaryResponse>
        get() = _anniversaryResponse

    fun addAnniversary(anniversaryDay: String, anniversary: String) {
        viewModelScope.launch {
            val response =
                Application.anniversaryRepository.addAnniversary(anniversaryDay, anniversary)
            if (response.isSuccessful) {
                _anniversaryResponse.postValue(response.body())
            } else {
                Log.e("AnniversaryViewModel", "err")
            }
        }
    }
}
