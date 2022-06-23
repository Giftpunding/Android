package com.giftpunding.osds.ui.bridge

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.ui.main.MainActivity
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityBridgeBinding
import com.giftpunding.osds.ui.address.AddressActivity
import com.giftpunding.osds.ui.TestViewBindingActivity
import com.giftpunding.osds.ui.bridge.adapter.BridgeAdapter
import com.giftpunding.osds.ui.funding.FundingListActivity
import com.giftpunding.osds.ui.home.HomeActivity
import com.giftpunding.osds.ui.login.LoginActivity
import com.giftpunding.osds.ui.search.SearchActivity

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
                MainActivity::class.java,
                LoginActivity::class.java,
                HomeActivity::class.java,
                SearchActivity::class.java,
                FundingListActivity::class.java,
                TestViewBindingActivity::class.java,
                AddressActivity::class.java
            )

            adapter = bridgeAdapter
            layoutManager = LinearLayoutManager(this@BridgeActivity)

            bridgeAdapter.addItems(mActivityList)
        }
    }
}