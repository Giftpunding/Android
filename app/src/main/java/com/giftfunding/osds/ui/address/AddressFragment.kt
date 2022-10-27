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
        binding.btnAddressSearch.setOnClickListener {
            navigate(AddressFragmentDirections.actionAddressFragmentToAddressSearchFragment())
        }

        //백 버튼 클릭시 어떤 화면으로 이동해야하나?
//        backButton.setOnClickListener { finish() }

        binding.btnNextTodo.setOnClickListener {
//            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    override fun initObserverEvent() {

    }

}