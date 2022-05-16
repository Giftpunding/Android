package com.giftpunding.osds.application

import android.app.Application
import com.giftpunding.osds.repository.LoginRepository
import com.giftpunding.osds.repository.local.pref.LoginSharedPreference
import com.giftpunding.osds.repository.remote.datasource.LoginRemoteDataSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application: Application() {

    companion object {
        lateinit var mApp: Application
        lateinit var loginRepository: LoginRepository
    }

    private lateinit var retrofit:Retrofit
    private lateinit var loginSharedPreference: LoginSharedPreference
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource


    override fun onCreate() {
        super.onCreate()
        mApp = this

        initNetworkModule()
        initDependency()

    }

    private fun initNetworkModule() {
        retrofit =
            Retrofit.Builder()
                .baseUrl("http://3.39.236.149")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    private fun initDependency() {
        loginSharedPreference = LoginSharedPreference(this@Application)
        loginRemoteDataSource = LoginRemoteDataSource(retrofit)
        loginRepository = LoginRepository(loginSharedPreference, loginRemoteDataSource)
    }
}