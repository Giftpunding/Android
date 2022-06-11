package com.giftpunding.osds.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.application.Application.Companion.loginRepository
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        private const val TAG: String = "LoginActivity..."
    }

    override fun init() {

    }

    override fun initEvent() {

    }
}