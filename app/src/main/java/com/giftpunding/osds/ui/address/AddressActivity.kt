package com.giftpunding.osds.ui.address

import android.content.Intent
import android.os.Bundle
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityAddressBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

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
            //Todo 어디로 이동할지 작성해야함
        }
    }
}