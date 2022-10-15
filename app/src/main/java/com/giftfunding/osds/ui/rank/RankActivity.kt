package com.giftfunding.osds.ui.rank

import android.os.Bundle
import androidx.activity.viewModels
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityRankBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType

class RankActivity : BaseActivity<ActivityRankBinding>(ActivityRankBinding::inflate) {

    private val viewModel: RankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        init()
        initEvent()
    }


    override fun init() {
        initToolbar()

    }

    override fun initEvent() {

    }

    private fun initToolbar() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButton(BackButton.BACK)
        setTitle(R.string.title_gift_ranking)
    }
}