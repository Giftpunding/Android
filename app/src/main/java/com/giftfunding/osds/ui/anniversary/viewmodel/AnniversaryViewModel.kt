package com.giftfunding.osds.ui.anniversary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.data.response.anniversary.AnniversaryResponse
import kotlinx.coroutines.launch

class AnniversaryViewModel : ViewModel() {

    //임시로 Anniversary response, request를 만들었음, 후에 User로 통합할 예정
    private val _anniversaryResponse = MutableLiveData<AnniversaryResponse>()
    val anniversaryResponse: LiveData<AnniversaryResponse>
        get() = _anniversaryResponse

    fun addAnniversary(anniversaryDay: String, anniversary: String) {
        viewModelScope.launch {
            //Callback이 아닌 Response를 반환
            val response =
                Application.anniversaryRepository.addAnniversary(anniversaryDay, anniversary)
            if (response.isSuccessful) {
                _anniversaryResponse.value = response.body()

            } else {
                Log.e("AnniversaryViewModel", "err")
            }
        }
    }
}
