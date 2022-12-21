package com.giftfunding.osds.data.repository.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class LoginSharedPreference(context: Context) {

    private val loginPrefs: SharedPreferences =
        context.getSharedPreferences("login", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = loginPrefs.getString("accessToken", "")
        set(value) = loginPrefs.edit().putString("accessToken", value).apply()

    var refreshToken: String?
        get() = loginPrefs.getString("refreshToken", "")
        set(value) = loginPrefs.edit().putString("refreshToken", value).apply()
}