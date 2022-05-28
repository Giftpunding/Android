package com.giftpunding.osds.ui.home.sougthAfter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftpunding.osds.data.response.home.merchandise.MerchandiseResponse
import com.giftpunding.osds.databinding.FragmentMerchandiseInfoBinding
import com.giftpunding.osds.databinding.FragmentSoughtAfterListBinding
import com.giftpunding.osds.ui.home.merchandise.MerchandiseInfoFragment

class SoughtAfterListFragment : Fragment() {

    private lateinit var binding: FragmentSoughtAfterListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoughtAfterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        soughtAfterListFragment = null
    }

    companion object {
        private var soughtAfterListFragment: Fragment? = null

        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(getList: List<MerchandiseResponse>): Fragment? {
            soughtAfterListFragment = SoughtAfterListFragment()
            soughtAfterListFragment?.arguments = Bundle().apply {

            }
            return soughtAfterListFragment
        }
    }
}
