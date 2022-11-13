package com.giftfunding.osds.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.giftfunding.osds.databinding.ActivityMainBinding
import com.giftfunding.osds.databinding.ContentToolbarBinding
import com.giftfunding.osds.ui.enum.ToolbarType
import com.giftfunding.osds.ui.enum.VisibleState

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity(){

    private var _binding: B? = null
    protected val binding get() = _binding!!

    private lateinit var toolbar: ContentToolbarBinding
//    private lateinit var activityTitle: TextView

    abstract fun init()
    abstract fun initEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private companion object {
        private val TAG: String = "BaseViewBindingActivity..."
    }
}