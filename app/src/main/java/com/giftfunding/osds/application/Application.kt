package com.giftfunding.osds.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.giftfunding.osds.BuildConfig
import com.giftfunding.osds.R
import com.giftfunding.osds.data.repository.AddressRepositoryImpl
import com.giftfunding.osds.data.repository.AnniversaryRepositoryImpl
import com.giftfunding.osds.data.repository.LoginRepositoryImpl
import com.giftfunding.osds.data.repository.SearchRepository
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreference
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreferenceImpl
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.data.repository.remote.datasource.AnniversaryDataSource
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
import com.giftfunding.osds.data.repository.remote.network.KakaoTokenInterceptor
import com.giftfunding.osds.data.repository.remote.network.NetworkModule
import com.giftfunding.osds.data.repository.remote.network.TokenInterceptor
import com.giftfunding.osds.domain.address.AddressRepository
import com.giftfunding.osds.domain.address.AddressUseCase
import com.giftfunding.osds.domain.anniversary.AnniversaryUseCase
import com.giftfunding.osds.domain.login.LoginRepository
import com.giftfunding.osds.domain.login.LoginUseCase
import com.kakao.sdk.common.KakaoSdk
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application: Application() {

    private lateinit var retrofit:Retrofit
    private lateinit var kakaoAddressRetrofit:Retrofit
    private lateinit var loginSharedPreference: LoginSharedPreference
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource
    private lateinit var keywordSharedPreference: KeywordSharedPreference
    private lateinit var anniversaryDataSource: AnniversaryDataSource
    private lateinit var anniversaryRepository: AnniversaryRepositoryImpl
    private lateinit var addressDataSource: AddressDataSource
    private lateinit var networkModule: NetworkModule
    private lateinit var tokenInterceptor: TokenInterceptor
    private lateinit var kakaoTokenInterceptor: KakaoTokenInterceptor
    private lateinit var loginRepository: LoginRepository
    private lateinit var searchRepository: SearchRepository
    private lateinit var addressRepository: AddressRepository

    override fun onCreate() {
        super.onCreate()
        mApp = this

        initDependency()
        initFlipper()
        KakaoSdk.init(this, getString(R.string.native_app_key))
    }

    private fun initDependency() {
        loginSharedPreference = LoginSharedPreference(this@Application)

        tokenInterceptor = TokenInterceptor(loginSharedPreference)
        kakaoTokenInterceptor = KakaoTokenInterceptor(getString(R.string.rest_api_key))

        networkModule = NetworkModule(tokenInterceptor, kakaoTokenInterceptor)
        retrofit = networkModule.retrofit
        kakaoAddressRetrofit = networkModule.kakaoRetrofit

        loginRemoteDataSource = LoginRemoteDataSource(retrofit)
        loginRepository = LoginRepositoryImpl(loginSharedPreference, loginRemoteDataSource)
        loginUseCase = LoginUseCase(loginRepository)

        keywordSharedPreference = KeywordSharedPreferenceImpl(this@Application)

        searchRepository = SearchRepository(keywordSharedPreference)

        anniversaryDataSource = AnniversaryDataSource(retrofit)
        anniversaryRepository = AnniversaryRepositoryImpl(anniversaryDataSource)
        anniversaryUseCase = AnniversaryUseCase(anniversaryRepository)

        addressDataSource = AddressDataSource(kakaoAddressRetrofit, retrofit)
        addressRepository = AddressRepositoryImpl(addressDataSource)
        addressUseCase = AddressUseCase(addressRepository)

    }

    private fun initFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }
    }



    companion object {
        lateinit var mApp: Application
        // 임시 DI 작업
        lateinit var loginUseCase: LoginUseCase
        lateinit var anniversaryUseCase: AnniversaryUseCase
        lateinit var addressUseCase: AddressUseCase
    }
}