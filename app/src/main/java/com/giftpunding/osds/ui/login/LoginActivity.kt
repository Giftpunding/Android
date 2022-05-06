package com.giftpunding.osds.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R

class LoginActivity : AppCompatActivity() {

    companion object{
        private const val TAG: String = "LoginActivity..."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}