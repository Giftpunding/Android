package com.giftfunding.osds.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityMainBinding
import com.giftfunding.osds.databinding.ContentToolbarBinding
import com.giftfunding.osds.ui.address.AddressDetailFragment
import com.giftfunding.osds.ui.address.AddressFragment
import com.giftfunding.osds.ui.address.AddressSearchFragment
import com.giftfunding.osds.ui.anniversary.AnniversarySelectFragment
import com.giftfunding.osds.ui.enum.ToolbarType
import com.giftfunding.osds.ui.enum.VisibleState
import com.giftfunding.osds.ui.home.HomeFragment
import com.giftfunding.osds.ui.ranking.GiftRankingFragment
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
            when (getCurrentFragment()){
                 is AnniversarySelectFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle("타이틀")
                }

                is AddressFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_setting_address))
                }

                is AddressSearchFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_address_search))
                }

                is AddressDetailFragment -> {
                    setToolbarType(ToolbarType.NORMAL, VisibleState.VISIBLE)
                    setTitle(resources.getString(R.string.title_address_detail))
                }

                is HomeFragment -> {
                    setToolbarType(ToolbarType.GIFT, VisibleState.VISIBLE)
                    setTitle("")
                }

                is GiftRankingFragment -> {
                    setToolbarType(ToolbarType.GIFT, VisibleState.VISIBLE)
                    setTitle(R.string.title_gift_ranking)
                }
            }
        }
    }

    private fun setToolbarType(type: ToolbarType, isShowClose: VisibleState){
        when(type){
            ToolbarType.NORMAL -> {
                normalToolbarType(isShowClose)
            }
            ToolbarType.GIFT -> {
                giftToolbarType()
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

    private fun giftToolbarType() {
        binding.contentToolbar.contentToolbarHead.apply {
            ivBack.visibility = View.GONE
            ivLogo.visibility = View.VISIBLE
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

    //현재 프래그먼트 찾기
    private fun getCurrentFragment(): Fragment? {
//        val currentFragmentContainer = supportFragmentManager.findFragmentById(R.id.fcv_main)
//        val currentFragmentClassName =
//            (navController.currentDestination as FragmentNavigator.Destination).className
//
//        Log.d(TAG, "getCurrentFragment: ${currentFragmentContainer?.childFragmentManager?.fragments}")
//
//        return currentFragmentContainer?.childFragmentManager?.fragments?.filterNotNull()?.find {
//            it.javaClass.name == currentFragmentClassName
//        }
        val currentFragmentContainer = supportFragmentManager.findFragmentById(R.id.fcv_main)
        val currentFragmentClassName =
            (navController.currentDestination)?.

        Log.d(TAG, "getCurrentFragment: ${currentFragmentContainer?.childFragmentManager?.fragments}")

        return currentFragmentContainer?.childFragmentManager?.fragments?.filterNotNull()?.find {
            it.javaClass.name == currentFragmentClassName
        }
    }

    companion object{
        private const val TAG = "MainActivity..."
    }
}