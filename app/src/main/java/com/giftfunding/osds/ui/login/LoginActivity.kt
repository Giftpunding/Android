package com.giftfunding.osds.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.giftfunding.osds.application.Application.Companion.loginRepository
import com.giftfunding.osds.ui.anniversary.AnniversarySelectActivity
import com.giftfunding.osds.ui.login.adapter.LoginBannerAdapter
import com.skydoves.balloon.*
import com.giftfunding.osds.R
import com.giftfunding.osds.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.ui.home.HomeActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()

    }

    override fun init() {
        checkAutoLogin()

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

    fun checkAutoLogin() {
        if(!LoginSharedPreference(this).getUserToken().isNullOrEmpty()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun initEvent() {
        kakaoLoginButtonEvent()
    }

    private fun kakaoLoginButtonEvent() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                // 로그인 성공 API 호출
                loginRepository.getJwt(token.accessToken)
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")

                finish()
                startActivity(Intent(this, AnniversarySelectActivity::class.java))
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
                        loginRepository.getJwt(token.accessToken)
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")

                        finish()
                        startActivity(Intent(this, AnniversarySelectActivity::class.java))
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