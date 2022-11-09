package com.giftfunding.osds.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

//토스트 메세지
fun showLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

//스낵바
fun showSnackBar(view: View, message: String) {
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    snackBar.show()
}

// 디바이스 높이 구하기
// status bar, navigation bar 제외
fun deviceHeight(context: Context) =
    context.resources.displayMetrics.heightPixels

// 현재 보여지고 있는 화면의 높이구하기
fun visibleDisplayFrameHeight(view: View) : Int{
    val visibleFrame = Rect()
    view.getWindowVisibleDisplayFrame(visibleFrame)
    return visibleFrame.bottom - visibleFrame.top
}
