package com.giftfunding.osds.ui.address

import android.os.Bundle
import android.view.View
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAddressBinding

class AddressFragment : BaseFragment<FragmentAddressBinding>(){

    override fun layoutResId(): Int = R.layout.fragment_address

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initObserverEvent()
    }

    override fun initEvent() {
        //TODO 백버튼 관련 처리 필요

        binding.btnAddressSearch.setOnClickListener {
            navigate(AddressFragmentDirections.actionAddressFragmentToAddressSearchFragment())
        }

        binding.btnNextTodo.setOnClickListener {
            navigate(AddressFragmentDirections.actionAddressFragmentToHomeFragment())
        }
    }

    override fun initObserverEvent() {

    }

}