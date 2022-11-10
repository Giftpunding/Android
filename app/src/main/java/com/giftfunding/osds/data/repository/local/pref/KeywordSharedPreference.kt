package com.giftfunding.osds.data.repository.local.pref

import org.json.JSONArray

interface KeywordSharedPreference {

    fun init(listener: Listener)

    fun readRecentKeyword()

    fun saveRecentKeyword()

    fun getRecentKeywordList(): ArrayList<String>

    fun addRecentKeyword(keyword: String)

    fun isExistKeyword(keyword: String): Boolean

    fun checkKeywordListSize(): Boolean

    fun deleteRecentKeywordOfPosition(position: Int)

    fun deleteRecentKeywordOfWord(keyword: String)

    fun clearRecentKeyword()

    fun modifyRecentKeyword(keyword: String?, position: Int?, type: KeywordSharedPreferenceImpl.Type)

    fun encodeJSONArray(keywordList: ArrayList<String>): JSONArray

    fun decodeJSONArray(json: String?): ArrayList<String>

    interface Listener{
        fun complete(recentKeywordList: ArrayList<String>)
    }
}