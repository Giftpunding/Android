package com.giftfunding.osds.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giftfunding.osds.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderGiftBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.content_order_product, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}