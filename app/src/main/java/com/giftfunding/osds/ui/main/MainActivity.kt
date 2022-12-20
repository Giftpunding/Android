package com.giftfunding.osds.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityMainBinding
import com.giftfunding.osds.ui.enum.ToolbarType
import com.giftfunding.osds.ui.enum.VisibleState
import com.giftfunding.osds.util.clearFocusAndHideKeyboard

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initEvent()
    }

    override fun init() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

    override fun initEvent() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            when (navController.currentDestination?.id){
                 R.id.anniversarySelectFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle("타이틀")
                }

                R.id.addressFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_setting_address))
                }

                R.id.addressSearchFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_address_search))
                }

                R.id.addressDetailFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.VISIBLE)
                    setTitle(resources.getString(R.string.title_address_detail))
                }

                R.id.homeFragment -> {
                    setToolbarType(ToolbarType.GIFT, VisibleState.VISIBLE)
                    setTitle("")
                }

                R.id.giftRankingFragment -> {
                    setToolbarType(ToolbarType.GIFT, VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_gift_ranking))
                }
            }
        }
    }

    private fun setToolbarType(type: ToolbarType, visibleState: VisibleState){
        when(type){
            ToolbarType.NORMAL -> {
                normalToolbarType(visibleState)
            }
            ToolbarType.GIFT -> {
                giftToolbarType(visibleState)
            }
        }
    }

    private fun normalToolbarType(isShowClose: VisibleState) {
        binding.contentToolbar.contentToolbarHead.apply {
            ivBack.visibility = View.VISIBLE
            ivLogo.visibility = View.GONE
        }

        binding.contentToolbar.contentToolbarTail.apply {
            when (isShowClose) {
                VisibleState.VISIBLE -> ivClose.visibility = View.VISIBLE
                VisibleState.INVISIBLE -> ivClose.visibility = View.GONE
            }
            contentGiftIcon.root.visibility = View.GONE
        }
    }

    private fun giftToolbarType(isShowLogo: VisibleState) {
        binding.contentToolbar.contentToolbarHead.apply {
            when(isShowLogo){
                VisibleState.VISIBLE -> {
                    ivBack.visibility = View.GONE
                    ivLogo.visibility = View.VISIBLE
                }
                VisibleState.INVISIBLE -> {
                    ivBack.visibility = View.VISIBLE
                    ivLogo.visibility = View.GONE
                }
            }
        }

        binding.contentToolbar.contentToolbarTail.apply {
            ivClose.visibility = View.GONE
            contentGiftIcon.root.visibility = View.VISIBLE
        }
    }

    private fun setTitle(title: String){
        binding.contentToolbar.tvToolbarTitle.text = title
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val currentView = currentFocus

        if(currentView is EditText){
            if(ev?.action == MotionEvent.ACTION_DOWN){
                val outRect = Rect()
                currentView.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    currentView.clearFocusAndHideKeyboard(context = this)
                    Log.d(TAG, "MotionEvent.ACTION_DOWN")
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    companion object{
        private const val TAG = "MainActivity..."
    }
}