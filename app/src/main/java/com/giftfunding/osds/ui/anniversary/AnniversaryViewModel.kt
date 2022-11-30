package com.giftfunding.osds.ui.anniversary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.data.repository.remote.datasource.dto.anniversary.AnniversaryResponse
import kotlinx.coroutines.launch

class AnniversaryViewModel: ViewModel() {

    // 성공, 에러에 관해서 서버에서 내려주는 code 값에 대한 관리는 정해지지 않음
    // 현재 int 형으로 내려오기 때문에 정해지기 전까지 int 타입으로 관리
    private val _anniversaryResponse = MutableLiveData<ViewState<Int>>()
    val anniversaryResponse: LiveData<ViewState<Int>>
        get() = _anniversaryResponse

    fun addAnniversary(anniversaryDay: String, anniversary: String) {
        viewModelScope.launch {
            _anniversaryResponse.value = ViewState.Loading()
            val response = Application.anniversaryUseCase.addAnniversary(anniversary, anniversaryDay)
            if(response.code == 204){
                _anniversaryResponse.value = ViewState.Success(response.code)
            }else{
                _anniversaryResponse.value = ViewState.Error(response.message, response.code)
            }
        }
    }
}
