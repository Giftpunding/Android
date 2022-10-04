package com.giftfunding.osds.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.giftfunding.osds.R
import com.giftfunding.osds.databinding.ContentGiftToolbarBinding
import com.giftfunding.osds.databinding.ContentToolbarBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    private lateinit var normalToolbarBinding: ContentToolbarBinding
    private lateinit var giftToolbarBinding: ContentGiftToolbarBinding

    protected lateinit var backButton: ImageView
    protected lateinit var activityTitle: TextView
    protected lateinit var closeButton: ImageView

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

    protected fun setToolbarType(type: ToolbarType) {
        when (type) {
            ToolbarType.NORMAL -> {
                normalToolbarType()
            }

            ToolbarType.GIFT -> {
                giftToolbarType()
            }
        }
    }

    private fun normalToolbarType() {
        normalToolbarBinding = ContentToolbarBinding.bind(binding.root)

        backButton = normalToolbarBinding.ivBack
        activityTitle = normalToolbarBinding.tvToolbarTitle
        closeButton = normalToolbarBinding.ivClose
    }

    private fun giftToolbarType(){
        giftToolbarBinding = ContentGiftToolbarBinding.bind(binding.root)
    }


    protected fun setBackButton(type: BackButton) {
        when (type) {
            BackButton.ARROW_BACK -> backButton.setImageResource(R.drawable.ic_arrow_back)
            BackButton.BACK -> backButton.setImageResource(R.drawable.ic_back)
        }
    }

    protected fun setBackButtonVisible(state: VisibleState) {
        when (state) {
            VisibleState.VISIBLE -> {
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

    protected fun setTitle(title: String) {
        activityTitle.text = title
    }

    protected fun setCloseButton(state: VisibleState) {
        when (state) {
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


    fun hideKeyboard(view: View) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun revealKeyboard(view: View) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    private companion object {
        private val TAG: String = "BaseViewBindingActivity..."
    }
}