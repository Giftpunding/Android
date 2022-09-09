package com.giftfunding.osds.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.giftfunding.osds.R
import com.giftfunding.osds.application.Application
import java.util.*

fun TextView.setAddressTextColor(address: String, keyword: String) {
    this.text = ""

    val st = StringTokenizer(address)

    while (st.hasMoreTokens()) {
        val token = st.nextToken()
        if (keyword.indexOf(token) > -1 || token.indexOf(keyword) > -1) {
            val ssb = SpannableStringBuilder("$token ")
            ssb.apply {
                if (token.length > keyword.length) {
                    setSpan(
                        ForegroundColorSpan(
                            ResourcesCompat.getColor(
                                Application.mApp.resources,
                                R.color.dodger_blue,
                                null
                            )
                        ), 0, keyword.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else {
                    setSpan(
                        ForegroundColorSpan(
                            ResourcesCompat.getColor(
                                Application.mApp.resources,
                                R.color.dodger_blue,
                                null
                            )
                        ),
                        0,
                        token.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            this.append(ssb)
        } else {
            this.append("$token ")
        }
    }
}