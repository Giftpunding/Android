package com.giftpunding.osds.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

abstract class DemoActivity: AppCompatActivity() {

    //생성자로 받는게 좋은 방법이 아니여서 해둔 건가?
    @LayoutRes
    protected abstract fun layoutRes(): Int
    private lateinit var backButton: ImageView
    private lateinit var activityTitle: TextView
    private lateinit var closeButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }

    //툴바가 선물인지 평범한 것인지 정함
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

    //평범한 툴바 초기화 부분?
    private fun normalToolbarType(){
        backButton = findViewById(R.id.iv_back)
        activityTitle = findViewById(R.id.tv_toolbar_title)
        closeButton = findViewById(R.id.iv_close)
    }

    //선물 툴바 초기화 부분
    private fun giftToolbarType(){

    }

    //backButton 달아주기
    protected fun setBackButton(type: BackButton){
        when(type){
            BackButton.ARROW_BACK -> backButton.setImageResource(R.drawable.ic_arrow_back)
            BackButton.BACK -> backButton.setImageResource(R.drawable.ic_back)
        }
    }

    //backButton Visibility
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

    //타이틀 정하기
    protected fun setTitle(title: String){
        activityTitle.text = title
    }

    //닫기 버튼 Visibility
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

    fun hideKeyboard(view: View) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun revealKeyboard(view: View) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

}