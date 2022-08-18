package com.giftpunding.osds.ui.login

import android.os.Bundle
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityAnniversarySelectBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class AnniversarySelectActivity :
    BaseActivity<ActivityAnniversarySelectBinding>(ActivityAnniversarySelectBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initEvent()
    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.VISIBLE)
        setBackButton(BackButton.BACK)
        setCloseButton(VisibleState.INVISIBLE)
        setTitle("텍스트")
    }

    override fun initEvent() {
    }
}