package com.giftpunding.osds.repository.local.pref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

class LoginSharedPreference(private val context: Context) {

    companion object{
        private const val TAG: String = "LoginSharedPreference"
    }

    private val loginSharedPreference: SharedPreferences =
        context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun setUserToken(userToken: String){
        loginSharedPreference.edit(commit = false){
            putString("jwt", userToken)
        }
    }

    fun getUserToken():String? = loginSharedPreference.getString("jwt", "")

}