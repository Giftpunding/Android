package com.giftfunding.osds.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.findNavController()

    }

    override fun init() {

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
                    setCloseButton(VisibleState.VISIBLE)
                    setTitle(resources.getString(R.string.title_setting_address))
                }
            }
        }
    }

    companion object{
        private const val TAG = "MainActivity..."
    }
}