package com.giftpunding.osds.ui

import android.os.Bundle
import android.util.Log
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity

class TestActivity: BaseActivity(){
    override fun layoutRes(): Int = R.layout.activity_test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "test Activity")

        setToolbarTitle("검색")
    }

    private companion object{
        private val TAG: String = "1111111..."
    }
}