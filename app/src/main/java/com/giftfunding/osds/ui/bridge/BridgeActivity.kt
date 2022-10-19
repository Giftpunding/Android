package com.giftfunding.osds.ui.bridge

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityBridgeBinding
import com.giftfunding.osds.ui.address.AddressActivity
import com.giftfunding.osds.ui.anniversary.AnniversarySelectActivity
import com.giftfunding.osds.ui.bridge.adapter.BridgeAdapter
import com.giftfunding.osds.ui.funding.FundingGiftListActivity
import com.giftfunding.osds.ui.home.HomeActivity
import com.giftfunding.osds.ui.keyhash.KeyHashActivity
import com.giftfunding.osds.ui.login.LoginActivity
import com.giftfunding.osds.ui.ranking.GiftRankingActivity
import com.giftfunding.osds.ui.search.SearchActivity

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
                HomeActivity::class.java,
                SearchActivity::class.java,
                FundingGiftListActivity::class.java,
                AddressActivity::class.java,
                AnniversarySelectActivity::class.java,
                GiftRankingActivity::class.java,
                KeyHashActivity::class.java
            )

            adapter = bridgeAdapter
            layoutManager = LinearLayoutManager(this@BridgeActivity)

            bridgeAdapter.addItems(mActivityList)
        }
    }
}