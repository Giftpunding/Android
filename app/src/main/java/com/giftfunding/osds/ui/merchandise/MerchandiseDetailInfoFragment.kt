package com.giftfunding.osds.ui.merchandise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftfunding.osds.databinding.FragmentMerchandiseDetailInfoBinding

class MerchandiseDetailInfoFragment : Fragment() {

    private lateinit var binding: FragmentMerchandiseDetailInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMerchandiseDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        merchandiseDetailInfoFragment = null
    }

    companion object {
        private var merchandiseDetailInfoFragment: Fragment? = null
        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(): Fragment? {
            merchandiseDetailInfoFragment = MerchandiseDetailInfoFragment()
            return merchandiseDetailInfoFragment
        }
    }
}