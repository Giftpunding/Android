package com.giftfunding.osds.data.repository

import android.util.Log
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreference
import com.giftfunding.osds.data.repository.local.pref.KeywordSharedPreferenceImpl

class SearchRepository(
    private val keywordSharedPreference: KeywordSharedPreference
) {
    private lateinit var listener: KeywordSharedPreference.Listener

    fun init(listener: KeywordSharedPreference.Listener) {
        this.listener = listener
        keywordSharedPreference.init(listener)
    }

    fun getRecentKeyword() =
        keywordSharedPreference.getRecentKeywordList()

    fun addRecentKeyword(keyword: String) {
        if (keyword != "") {
            Log.d(TAG, "add recent keyword")
            keywordSharedPreference.modifyRecentKeyword(
                keyword,
                null,
                KeywordSharedPreferenceImpl.Type.ADD
            )
        }
    }

    fun deleteRecentKeyword(itemPosition: Int) {
        Log.d(TAG, "delete recent keyword")
        keywordSharedPreference.modifyRecentKeyword(
            null,
            itemPosition,
            KeywordSharedPreferenceImpl.Type.DELETE
        )
    }

    fun deleteAllRecentKeyword() {
        Log.d(TAG, "delete All recent keyword")
        keywordSharedPreference.modifyRecentKeyword(
            null,
            null,
            KeywordSharedPreferenceImpl.Type.CLEAR
        )
    }

    companion object {
        private const val TAG: String = "LoginRepository ...."
    }
}