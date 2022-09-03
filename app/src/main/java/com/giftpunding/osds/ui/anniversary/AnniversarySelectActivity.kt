package com.giftpunding.osds.ui.anniversary

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityAnniversarySelectBinding
import com.giftpunding.osds.enum.BackButton
import com.giftpunding.osds.enum.ToolbarType
import com.giftpunding.osds.enum.VisibleState

class AnniversarySelectActivity :
    BaseActivity<ActivityAnniversarySelectBinding>(ActivityAnniversarySelectBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        binding.tvBirthday.setOnClickListener { replaceFragment(BIRTHDAY) }
        binding.tvPregnancy.setOnClickListener { replaceFragment(PREGNANCY) }
        binding.tvHousewarming.setOnClickListener { replaceFragment(HOUSEWARMING) }
        binding.tvWedding.setOnClickListener { replaceFragment(WEDDING) }
    }

    private fun replaceFragment(anniversaryType: String) {
        val anniversarySelectFragment = AnniversarySelectFragment().apply {
            arguments = Bundle().apply {
                putString(ANNIVERSARY_TYPE, anniversaryType)
            }
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.flAnniversarySelect.id, anniversarySelectFragment).commit()
    }

    companion object {
        const val ANNIVERSARY_TYPE = "anniversary_type"
        const val BIRTHDAY = "birthday"
        const val PREGNANCY = "pregnancy"
        const val WEDDING = "wedding"
        const val HOUSEWARMING = "housewarming"
    }
}