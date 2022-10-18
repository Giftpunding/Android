package com.giftfunding.osds.ui.anniversary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAnniversarySelectBinding
import com.giftfunding.osds.enum.AnniversaryType
import com.giftfunding.osds.ui.anniversary.AnniversarySelectFragmentDirections
import com.giftfunding.osds.util.showLongToast

class AnniversarySelectFragment : BaseFragment<FragmentAnniversarySelectBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnniversarySelectBinding {
        return FragmentAnniversarySelectBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnniversaryEvent()
    }

    private fun validateUserInput() : Boolean{
        return binding.editUserInput.text.toString() != ""
    }

    private fun addAnniversaryEvent(){
        binding.apply {
            tvBirthday.setOnClickListener{navigate(AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(AnniversaryType.BIRTHDAY))}
            tvPregnancy.setOnClickListener{navigate(AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(AnniversaryType.PREGNANCY))}
            tvHousewarming.setOnClickListener{navigate(AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(AnniversaryType.HOUSEWARMING))}
            tvWedding.setOnClickListener{navigate(AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(AnniversaryType.WEDDING))}

            editUserInput.setOnEditorActionListener { _, actionId, _ ->
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    if(validateUserInput()){
                        val userInput = binding.editUserInput.text.toString()
                        navigate(AnniversarySelectFragmentDirections.actionAnniversarySelectFragmentToAnniversaryDateSelectFragment(AnniversaryType.USER_INPUT,userInput))
                    }else{
                        showLongToast(getString(R.string.content_empty_user_input_anniversary))
                    }
                }
                false
            }
        }
    }
}