package com.giftpunding.osds.ui.anniversary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftpunding.osds.application.Application
import kotlinx.coroutines.launch

class AnniversaryFragmentViewModel : ViewModel() {


    fun addAnniversary(anniversaryDay: String, anniversary: String) {
        //viewModelScope는 dispatcher가 기본으로 main으로 된다.
        //서버랑 통신하는데 main으로 유지되면 ???! main은 main 스레드이다
        //main 스레드에서 서버와 통신은 원래 맞지 않는다...  하지만 retrofit에서는 자동으로 main-> io로 변경해준다
        //room 같은 경우는 coroutineScope(dispatcher.io)를 사용해야한다.
        viewModelScope.launch {
            Application.anniversaryRepository.addAnniversary(anniversaryDay, anniversary)
        }
    }
}