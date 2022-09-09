package com.giftfunding.osds.ui.address

import android.content.Intent
import android.os.Bundle
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityAddressBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState
import com.giftfunding.osds.ui.home.HomeActivity

class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.VISIBLE)
        setBackButton(BackButton.BACK)
        setTitle(resources.getString(R.string.title_setting_address))
        setCloseButton(VisibleState.INVISIBLE)
    }

    override fun initEvent() {
        binding.btnAddressSearch.setOnClickListener {
            startActivity(Intent(this, AddressSearchActivity::class.java))
        }

        backButton.setOnClickListener { finish() }

        binding.btnNextTodo.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}