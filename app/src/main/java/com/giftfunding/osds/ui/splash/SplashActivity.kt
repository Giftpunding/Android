package com.giftfunding.osds.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.databinding.ActivitySplashBinding
import com.giftfunding.osds.ui.login.LoginActivity
import com.giftfunding.osds.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initAccessTokenObserver()
        initRefreshTokenObserver()
        initUserTokenObserver()

        val handler = Handler(mainLooper)
        handler.postDelayed({ splashViewModel.getUserAccessToken() }, 1000)
    }

    private fun initAccessTokenObserver() {
        splashViewModel.userAccessToken.observe(this) { accessToken ->
            tokenValidate(accessToken)
        }
    }

    /** 토큰 검증 */
    private fun tokenValidate(token: String) {
        if (token.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        } else {
            splashViewModel.getUserRefreshToken()
        }
    }

    private fun initRefreshTokenObserver() {
        splashViewModel.userRefreshToken.observe(this) { refreshToken ->
            splashViewModel.getTokenWithRefreshToken(refreshToken)
        }
    }

    private fun initUserTokenObserver() {
        //에러 핸들링은 추후에 적용
        splashViewModel.userToken.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
                is ViewState.Error -> {
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                }
            }
        }
    }
}