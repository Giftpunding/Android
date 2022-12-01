package com.giftfunding.osds.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.giftfunding.osds.BuildConfig
import com.giftfunding.osds.R
import com.giftfunding.osds.data.repository.AddressRepository
import com.giftfunding.osds.data.repository.AnniversaryRepository
import com.giftfunding.osds.data.repository.LoginRepositoryImpl
import com.giftfunding.osds.data.repository.SearchRepository
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreference
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreferenceImpl
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.data.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.data.repository.remote.datasource.AnniversaryDataSource
import com.giftfunding.osds.data.repository.remote.datasource.LoginRemoteDataSource
import com.giftfunding.osds.domain.login.LoginRepository
import com.giftfunding.osds.domain.login.LoginUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {

    private lateinit var retrofit: Retrofit
    private lateinit var kakaoAddressRetrofit: Retrofit
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource
    private lateinit var keywordSharedPreference: KeywordSharedPreference
    private lateinit var anniversaryDataSource: AnniversaryDataSource
    private lateinit var addressDataSource: AddressDataSource
    private lateinit var gsonConvert: Gson

    override fun onCreate() {
        super.onCreate()
        mApp = this

        initNetworkModule()
        initGson()
        initDependency()
        initFlipper()
        KakaoSdk.init(this, getString(R.string.native_app_key))
    }

    private fun initNetworkModule() {
        val interceptor = Interceptor {
            with(it) {
                val newRequest =
                    request().newBuilder()
                        .addHeader(
                            "Authorization",
                            loginSharedPreference.accessToken ?: ""
                        )
                        .build()

                proceed(newRequest)
            }
        }

        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        kakaoAddressRetrofit =
            Retrofit.Builder()
                .baseUrl(kakaoBaseUrl)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initGson() {
        gsonConvert = GsonBuilder().create()
    }

    private fun initDependency() {
        loginSharedPreference = LoginSharedPreference(this)
        loginRemoteDataSource = LoginRemoteDataSource(retrofit)

        keywordSharedPreference = KeywordSharedPreferenceImpl(this@Application)

        loginRepository = LoginRepositoryImpl(loginSharedPreference, loginRemoteDataSource)
        searchRepository = SearchRepository(keywordSharedPreference)

        anniversaryDataSource = AnniversaryDataSource(retrofit)
        anniversaryRepository = AnniversaryRepository(anniversaryDataSource)

        addressDataSource = AddressDataSource(kakaoAddressRetrofit, retrofit)
        addressRepository = AddressRepository(addressDataSource)

        loginUseCase = LoginUseCase(loginRepository)

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
        const val baseUrl: String = "http://dev.taxijjang.site"
        const val kakaoBaseUrl: String = "https://dapi.kakao.com"
        lateinit var mApp: Application

        // 임시 DI 작업
        lateinit var loginRepository: LoginRepository
        lateinit var searchRepository: SearchRepository
        lateinit var anniversaryRepository: AnniversaryRepository
        lateinit var addressRepository: AddressRepository
        lateinit var loginUseCase: LoginUseCase
        lateinit var loginSharedPreference: LoginSharedPreference
    }
}