package com.giftfunding.osds.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityMainBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState

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
        navController.addOnDestinationChangedListener { controller, _, _ ->
            when ((controller.currentDestination as FragmentNavigator.Destination).className){
                //클래스 이름으로 어떤 fragment인지 찾음
                "com.giftfunding.osds.ui.anniversary.AnniversarySelectFragment" -> {
                    setToolbarType(ToolbarType.NORMAL)
                    setBackButtonVisible(VisibleState.VISIBLE)
                    setBackButton(BackButton.BACK)
                    setCloseButton(VisibleState.INVISIBLE)
                    setTitle("타이틀")
                }

                "com.giftfunding.osds.ui.address.AddressFragment" -> {
                    setToolbarType(ToolbarType.NORMAL)
                    setBackButtonVisible(VisibleState.VISIBLE)
                    setBackButton(BackButton.BACK)
                    setCloseButton(VisibleState.INVISIBLE)
                    setTitle(resources.getString(R.string.title_setting_address))
                }

                "com.giftfunding.osds.ui.address.AddressSearchFragment" -> {
                    setToolbarType(ToolbarType.NORMAL)
                    setBackButtonVisible(VisibleState.INVISIBLE)
                    setCloseButton(VisibleState.VISIBLE)
                    setTitle(resources.getString(R.string.title_address_search))
                }
            }
        }
    }

    companion object{
        private const val TAG = "MainActivity..."
    }
}