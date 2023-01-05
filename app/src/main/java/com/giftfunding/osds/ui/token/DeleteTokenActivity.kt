package com.giftfunding.osds.ui.token

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.giftfunding.osds.R
import com.giftfunding.osds.application.Application
import com.giftfunding.osds.databinding.ActivityDeleteTokenBinding
import com.giftfunding.osds.util.showLongToast

class DeleteTokenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDeleteTokenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_token)

        binding.btnDeleteToken.setOnClickListener {
            Application.tokenDeleteRepo.deleteUserAccessToken()
            Application.tokenDeleteRepo.deleteUserRefreshToken()
            showLongToast("토큰 삭제 완료")
        }
    }
}