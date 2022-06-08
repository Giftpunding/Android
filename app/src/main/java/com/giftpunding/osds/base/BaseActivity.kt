package com.giftpunding.osds.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.ui.TestActivity
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutRes(): Int
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        Log.d(TAG, "BaseActivity..")

        setToolbar()
    }

    private fun setToolbar(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.apply {
            isTitleCentered = true
        }
    }

    @SuppressLint("ResourceAsColor")
    fun setToolbarTitle(title: String){
        Log.d(TAG, "BaseActivity.. $title")
        this.toolbar.title = title
    }

    private companion object{
        private val TAG: String = "1111111..."
    }
}