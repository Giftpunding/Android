package com.giftfunding.osds.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.giftfunding.osds.BuildConfig
import com.giftfunding.osds.R
import com.giftfunding.osds.repository.AddressRepository
import com.giftfunding.osds.repository.AnniversaryRepository
import com.giftfunding.osds.repository.LoginRepository
import com.giftfunding.osds.repository.SearchRepository
import com.giftfunding.osds.repository.local.pref.KeywordSharedPreference
import com.giftfunding.osds.repository.local.pref.KeywordSharedPreferenceImpl
import com.giftfunding.osds.repository.local.pref.LoginSharedPreference
import com.giftfunding.osds.repository.remote.datasource.AddressDataSource
import com.giftfunding.osds.repository.remote.datasource.AnniversaryDataSource
import com.giftfunding.osds.repository.remote.datasource.LoginRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import okhttp3.Interceptor
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
                            LoginSharedPreference(context = this@Application).getUserToken() ?: ""
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
        loginSharedPreference = LoginSharedPreference(this@Application)
        loginRemoteDataSource = LoginRemoteDataSource(retrofit)
        keywordSharedPreference = KeywordSharedPreferenceImpl(this@Application)

        loginRepository = LoginRepository(loginSharedPreference, loginRemoteDataSource)
        searchRepository = SearchRepository(keywordSharedPreference)

        anniversaryDataSource = AnniversaryDataSource(retrofit)
        anniversaryRepository = AnniversaryRepository(anniversaryDataSource)

        addressDataSource = AddressDataSource(kakaoAddressRetrofit, retrofit)
        addressRepository = AddressRepository(addressDataSource)
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
        const val baseUrl: String = "http://3.36.251.242:8080"
        const val kakaoBaseUrl : String = "https://dapi.kakao.com"
        lateinit var mApp: Application
        lateinit var loginRepository: LoginRepository
        lateinit var searchRepository: SearchRepository
        lateinit var anniversaryRepository: AnniversaryRepository
        lateinit var addressRepository: AddressRepository
    }
}