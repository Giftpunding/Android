package com.giftfunding.osds.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.appcompat.content.res.AppCompatResources

fun EditText.textClear(){

}

fun EditText.changeBackgroundColor(
    @NonNull context: Context,
    @DrawableRes backgroundResId: Int,
) {
    background = AppCompatResources.getDrawable(
        context,
        backgroundResId
    )
}

fun EditText.hideSearchIcon() {
    setCompoundDrawablesWithIntrinsicBounds(
        0,
        0,
        0,
        0
    )
}

fun EditText.showSearchIcon(
    @DrawableRes icon: Int
) {
    setCompoundDrawablesWithIntrinsicBounds(
        icon,
        0,
        0,
        0
    )
}

fun EditText.setFocusAndShowKeyboard(context: Context) {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, 0)
}

fun EditText.clearFocusAndHideKeyboard(context: Context) {
    this.clearFocus()
    this.postDelayed({
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }, 30)
}