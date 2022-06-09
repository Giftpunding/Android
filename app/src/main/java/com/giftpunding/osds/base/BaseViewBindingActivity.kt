package com.giftpunding.osds.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ContentGiftToolbarBinding
import com.giftpunding.osds.databinding.ContentToolbarBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

abstract class BaseViewBindingActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    private lateinit var normalToolbarBinding: ContentToolbarBinding
    private lateinit var giftToolbarBinding: ContentGiftToolbarBinding

    private lateinit var backButton: ImageView
    private lateinit var activityTitle: TextView
    private lateinit var closeButton: ImageView


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

    private fun giftToolbarType() {
        giftToolbarBinding = ContentGiftToolbarBinding.bind(binding.root)
        //input view
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

    private companion object {
        private val TAG: String = "1111111..."
    }
}