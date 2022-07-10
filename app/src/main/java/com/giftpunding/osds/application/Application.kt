package com.giftpunding.osds.application

import android.app.Application
import com.giftpunding.osds.R
import com.giftpunding.osds.repository.LoginRepository
import com.giftpunding.osds.repository.SearchRepository
import com.giftpunding.osds.repository.local.pref.KeywordSharedPreference
import com.giftpunding.osds.repository.local.pref.KeywordSharedPreferenceImpl
import com.giftpunding.osds.repository.local.pref.LoginSharedPreference
import com.giftpunding.osds.repository.remote.datasource.LoginRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application: Application() {

    private lateinit var retrofit:Retrofit
    private lateinit var loginSharedPreference: LoginSharedPreference
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource
    private lateinit var keywordSharedPreference: KeywordSharedPreference
    private lateinit var gsonConvert: Gson

    override fun onCreate() {
        super.onCreate()
        mApp = this

        initNetworkModule()
        initGson()
        initDependency()
        KakaoSdk.init(this, getString(R.string.native_app_key))

    }

    private fun initNetworkModule() {

        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
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
    }

    companion object {
        const val baseUrl: String = "http://3.39.236.149"
        lateinit var mApp: Application
        lateinit var loginRepository: LoginRepository
        lateinit var searchRepository: SearchRepository
    }
}