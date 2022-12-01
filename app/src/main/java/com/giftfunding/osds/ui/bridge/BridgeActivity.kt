package com.giftfunding.osds.ui.bridge

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityBridgeBinding
import com.giftfunding.osds.ui.bridge.adapter.BridgeAdapter
import com.giftfunding.osds.ui.keyhash.KeyHashActivity
import com.giftfunding.osds.ui.login.LoginActivity
import com.giftfunding.osds.ui.main.MainActivity
import com.giftfunding.osds.ui.splash.SplashActivity

class BridgeActivity: BaseActivity<ActivityBridgeBinding>(ActivityBridgeBinding::inflate) {

    private lateinit var mRvBridge: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()
        initRecyclerView()
    }

    private fun initialized() {
        mRvBridge = findViewById(R.id.rv_bridge)
    }

    override fun init() {

    }

    override fun initEvent() {

    }

    private fun initRecyclerView() {
        mRvBridge.apply {
            val bridgeAdapter = BridgeAdapter(this@BridgeActivity)

            val mActivityList: ArrayList<Class<out Activity>> = arrayListOf(
                LoginActivity::class.java,
                SplashActivity::class.java,
//                SearchActivity::class.java,
//                FundingGiftListActivity::class.java,
                KeyHashActivity::class.java,
                MainActivity::class.java
            )

            adapter = bridgeAdapter
            layoutManager = LinearLayoutManager(this@BridgeActivity)

            bridgeAdapter.addItems(mActivityList)
        }
    }
}