package com.giftfunding.osds.util

import android.content.Context
import android.widget.Toast

object Util {
    fun showLongToast(context: Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}