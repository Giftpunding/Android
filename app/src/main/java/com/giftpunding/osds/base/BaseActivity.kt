package com.giftpunding.osds.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    protected abstract fun layoutRes(): Int
    private lateinit var backButton: ImageView
    private lateinit var activityTitle: TextView
    private lateinit var closeButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }

    protected fun setToolbarType(type: ToolbarType){
        when(type){
            ToolbarType.NORMAL -> {
                normalToolbarType()
            }

            ToolbarType.GIFT ->{
                giftToolbarType()
            }
        }
    }

    private fun normalToolbarType(){
        backButton = findViewById(R.id.iv_back)
        activityTitle = findViewById(R.id.tv_toolbar_title)
        closeButton = findViewById(R.id.iv_close)
    }

    private fun giftToolbarType(){

    }


    protected fun setBackButton(type: BackButton){
        when(type){
            BackButton.ARROW_BACK -> backButton.setImageResource(R.drawable.ic_arrow_back)
            BackButton.BACK -> backButton.setImageResource(R.drawable.ic_back)
        }
    }

    protected fun setBackButtonVisible(state: VisibleState){
        when(state){
            VisibleState.VISIBLE ->{
                backButton.apply {
                    visibility = View.VISIBLE
                }
            }

            VisibleState.INVISIBLE -> {
                backButton.apply {
                    visibility = View.INVISIBLE
                }
            }
        }
    }

    protected fun setTitle(title: String){
        activityTitle.text = title
    }

    protected fun setCloseButton(state: VisibleState){
        when(state){
            VisibleState.VISIBLE -> {
                closeButton.apply {
                    visibility = View.VISIBLE
                }
            }

            VisibleState.INVISIBLE -> {
                closeButton.apply {
                    visibility = View.INVISIBLE
                }
            }
        }
    }

    private companion object{
        private val TAG: String = "1111111..."
    }
}