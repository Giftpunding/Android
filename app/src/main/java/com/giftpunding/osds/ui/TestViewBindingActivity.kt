package com.giftpunding.osds.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.giftpunding.osds.base.BaseViewBindingActivity
import com.giftpunding.osds.databinding.ActivityTestBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class TestViewBindingActivity :
    BaseViewBindingActivity<ActivityTestBinding>(ActivityTestBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarType(ToolbarType.NORMAL)
        setBackButton(BackButton.ARROW_BACK)
        setBackButtonVisible(VisibleState.VISIBLE)
        setTitle("테스트입니다")
        setCloseButton(VisibleState.VISIBLE)
    }
}