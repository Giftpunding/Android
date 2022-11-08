package com.giftfunding.osds.ui.address

import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import com.giftfunding.osds.util.visibleDisplayFrameHeight

class KeyboardImpl {

    private var deviceHeight: Int = 0
    private lateinit var view : View
    private lateinit var listener: Keyboard

    private val viewTree = ViewTreeObserver.OnGlobalLayoutListener {
        val currentView = visibleDisplayFrameHeight(this.view)
        val keyboardHeight = this.deviceHeight - currentView

        if (isShowKeyboard(keyboardHeight)) {
            this.listener.keyboardShow(keyboardHeight)
        }else{
            this.listener.keyboardHide(keyboardHeight)
        }
    }

    fun init(view: View, deviceHeight: Int, listener: Keyboard) {
        this.view = view
        this.deviceHeight = deviceHeight
        this.listener = listener
    }


    private fun isShowKeyboard(keyboardHeight: Int): Boolean =
        keyboardHeight != 0

    fun onGlobalLayout() {
        this.view.viewTreeObserver.addOnGlobalLayoutListener(viewTree)
    }

    fun remove(){
        Log.d("KeyboardImpl", "remove")
        this.view.viewTreeObserver.removeOnGlobalLayoutListener(viewTree)
    }
}