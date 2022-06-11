package com.giftpunding.osds.ui

import android.os.Bundle
import android.util.Log
import com.giftpunding.osds.R
import com.giftpunding.osds.base.DemoActivity
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class TestActivity: DemoActivity(){
    override fun layoutRes(): Int = R.layout.activity_test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "test Activity")

        // gift 모양 아이콘 없을 때
        setToolbarType(ToolbarType.NORMAL)
        setBackButton(BackButton.ARROW_BACK)
        setBackButtonVisible(VisibleState.VISIBLE)
        setTitle("TEST")
        setCloseButton(VisibleState.VISIBLE)

        //gift 모양 아이콘 있을떄
//        setToolbarType(ToolbarType.GIFT)


    }

    private companion object{
        private val TAG: String = "1111111..."
    }
}