package com.giftpunding.osds.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.giftpunding.osds.R

object SpannableString {

    @SuppressLint("ResourceAsColor")
    fun setTextColor(address: String, keyword: String): SpannableStringBuilder {
        val sp = SpannableStringBuilder(address)

        val startIndex = getStartIndex(address, keyword)
        val endIndex = startIndex + (keyword.length)
        sp.setSpan(ForegroundColorSpan(R.color.dodger_blue), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return sp
    }

    private fun getStartIndex(address: String, keyword: String) = address.indexOf(keyword)

}