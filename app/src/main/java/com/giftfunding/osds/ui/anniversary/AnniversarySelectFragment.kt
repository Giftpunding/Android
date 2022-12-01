package com.giftfunding.osds.ui.anniversary

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAnniversarySelectBinding
import com.giftfunding.osds.ui.enum.AnniversaryType
import com.giftfunding.osds.util.showLongToast

class AnniversarySelectFragment : BaseFragment<FragmentAnniversarySelectBinding>() {

    override fun layoutResId(): Int = R.layout.fragment_anniversary_select

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    override fun initEvent() {
        binding.apply {
            tvBirthday.setOnClickListener {
                changeFragment(AnniversaryType.BIRTHDAY)
            }
            tvPregnancy.setOnClickListener {
                changeFragment(AnniversaryType.PREGNANCY)
            }
            tvHousewarming.setOnClickListener {
                changeFragment(AnniversaryType.HOUSEWARMING)
            }
            tvWedding.setOnClickListener {
                changeFragment(AnniversaryType.WEDDING)
            }

            editUserInput.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (validateUserInput()) {
                        changeFragment(AnniversaryType.USER_INPUT)
                    } else {
                        showLongToast(
                            requireContext(),
                            getString(R.string.content_empty_user_input_anniversary)
                        )
                    }
                }
                false
            }
        }
    }

    private fun validateUserInput(): Boolean = binding.editUserInput.text.toString() != ""


    override fun initObserverEvent() {

    }

    private fun changeFragment(anniversary: AnniversaryType) {
        navigate(
            AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(
                anniversary,
                binding.editUserInput.text.toString()
            )
        )
    }
}