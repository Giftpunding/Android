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

        addNavigationChangedListener()
    }

    private fun addNavigationChangedListener(){
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when ((controller.currentDestination as FragmentNavigator.Destination).className){
                "com.giftfunding.osds.ui.anniversary.AnniversarySelectFragment" -> {
                    setToolbarType(ToolbarType.NORMAL)
                    setBackButtonVisible(VisibleState.VISIBLE)
                    setBackButton(BackButton.BACK)
                    setCloseButton(VisibleState.INVISIBLE)
                    setTitle("타이틀")
                }
            }
        }
    }

    override fun init() {

    }

    override fun initEvent() {
        TODO("Not yet implemented")
    }

    companion object{
        private const val TAG = "MainActivity..."
    }
}