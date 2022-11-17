package com.giftfunding.osds.ui.login

import android.content.Intent
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.giftfunding.osds.ui.login.adapter.LoginBannerAdapter
import com.skydoves.balloon.*
import com.giftfunding.osds.R
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.ui.main.MainActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()
    }

    override fun init() {
        val list = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
        )

        binding.apply {
            vpBanner.adapter = LoginBannerAdapter(list, this@LoginActivity)
            ciBanner.setViewPager(vpBanner)
            btnKakaoLogin.setOnClickListener { kakaoLoginButtonEvent() }
            btnKakaoLogin.showAlignTop(makeBalloon())
        }

        initObserveLoginViewModel()
    }

    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(this)
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(resources.getString(R.string.content_login_tutorial))
            .setTextColorResource(R.color.hawkes_blue)
            .setTextTypeface(ResourcesCompat.getFont(this, R.font.pretendard_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setIconDrawableResource(R.drawable.ic_launcher_background)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(8)
            .setPaddingLeft(13)
            .setPaddingRight(13)
            .setPaddingBottom(8)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.bright_grey)
            .setDismissWhenClicked(false)
            .setDismissWhenOverlayClicked(false)
            .setDismissWhenTouchOutside(false)

        return popUpMessage.build()
    }

    override fun initEvent() {
        kakaoLoginButtonEvent()
    }

    private fun initObserveLoginViewModel() {
        loginViewModel.checkUserAccessToken.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    //로딩 다이얼로그 show
                }
                is ViewState.Success -> {
                    //로딩 다이얼로그 dismiss
                    if (response.value == true) {
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
                is ViewState.Error -> {
                    // 로딩 다이얼로그 dismiss
                }
            }
        }
    }

    private fun kakaoLoginButtonEvent() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                // 로그인 성공 API 호출
                loginViewModel.getUserJwt(token.accessToken)
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }

        binding.btnKakaoLogin.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        // 로그인 성공 API 호출
                        loginViewModel.getUserJwt(token.accessToken)
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    companion object {
        private const val TAG: String = "LoginActivity..."
    }
}