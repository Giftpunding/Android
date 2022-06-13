package com.giftpunding.osds.ui.address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
        setTitle("텍스트")
        setCloseButton(VisibleState.INVISIBLE)
    }

    override fun initEvent() {
        binding.btnAddressSearch.setOnClickListener {
            startActivity(Intent(this, AddressSearchActivity::class.java))
        }
    }
}