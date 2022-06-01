package com.giftpunding.osds.repository.local.pref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import org.json.JSONArray

class KeywordSharedPreferenceImpl(context: Context) : KeywordSharedPreference {

    private lateinit var keywordList: ArrayList<String>
    private lateinit var listener: KeywordSharedPreference.Listener
    private val keywordSharedPreference: SharedPreferences =
        context.getSharedPreferences("keyword", Context.MODE_PRIVATE)

    enum class Type {
        ADD,
        DELETE,
        CLEAR
    }

    override fun init(listener: KeywordSharedPreference.Listener) {
        this.listener = listener
    }

    override fun readRecentKeyword() {
        this.keywordList = decodeJSONArray(this.keywordSharedPreference.getString("keyword", null))
    }

    override fun saveRecentKeyword() {
        val keywordJSONArray: JSONArray = encodeJSONArray(this.keywordList)
        this.keywordSharedPreference.edit(commit = false) {
            putString("keyword", keywordJSONArray.toString())
        }

        this.listener.complete(getRecentKeywordList())
    }

    override fun getRecentKeywordList(): ArrayList<String> =
        decodeJSONArray(this.keywordSharedPreference.getString("keyword", null))

    override fun addRecentKeyword(keyword: String) {
        this.keywordList.add(keyword)
    }

    override fun isExistKeyword(keyword: String): Boolean =
        this.keywordList.contains(keyword)

    override fun checkKeywordListSize(): Boolean =
        this.keywordList.size >= maxRecentKeywordSize

    override fun deleteRecentKeywordOfPosition(position: Int) {
        this.keywordList.removeAt(position)
    }

    override fun deleteRecentKeywordOfWord(keyword: String) {
        this.keywordList.remove(keyword)
    }

    override fun clearRecentKeyword() {
        this.keywordList.clear()
    }

    override fun modifyRecentKeyword(keyword: String?, position: Int?, type: Type) {
        readRecentKeyword()

        when (type) {
            Type.ADD -> {
                Log.d(TAG, "add recent keyword")
                if (isExistKeyword(keyword!!)) {
                    deleteRecentKeywordOfWord(keyword)
                }

                if (checkKeywordListSize()) {
                    deleteRecentKeywordOfPosition(firstItem)
                }

                addRecentKeyword(keyword)
            }

            Type.DELETE -> {
                Log.d(TAG, "delete recent keyword")
                deleteRecentKeywordOfPosition(position!!)
            }

            Type.CLEAR -> {
                Log.d(TAG, "clear recent keyword")
                clearRecentKeyword()
            }
        }

        saveRecentKeyword()
    }

    override fun encodeJSONArray(keywordList: ArrayList<String>): JSONArray {
        val keywordJSONArray = JSONArray()
        for (i in 0 until keywordList.size) {
            keywordJSONArray.put(keywordList[i])
        }
        return keywordJSONArray

    }

    override fun decodeJSONArray(json: String?): ArrayList<String> {
        val keywordArrayList = ArrayList<String>()

        if (json != null) {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                keywordArrayList.add(jsonArray.optString(i))
            }
        }
        return keywordArrayList
    }

    companion object {
        private const val TAG: String = "KeywordSharedPreference"
        private const val firstItem: Int = 0
        private const val maxRecentKeywordSize: Int = 5
    }
}