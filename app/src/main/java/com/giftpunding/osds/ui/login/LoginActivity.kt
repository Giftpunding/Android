package com.giftpunding.osds.ui.login

import android.os.Bundle
import android.util.Log
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityLoginBinding
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()

    }

    override fun init() {

    }

    override fun initEvent() {
        kakaoLoginButtonEvent()
    }

    private fun kakaoLoginButtonEvent() {
        binding.btnKakaoLogin.setOnClickListener {
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "로그인 실패", error)
                } else if (token != null) {
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")
                }
            }
        }
    }

    companion object {
        private const val TAG: String = "LoginActivity..."
    }
}