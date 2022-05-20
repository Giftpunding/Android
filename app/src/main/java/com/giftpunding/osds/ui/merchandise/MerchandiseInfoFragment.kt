package com.giftpunding.osds.ui.merchandise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftpunding.osds.databinding.ActivityMerchandiseInfoBinding
import com.giftpunding.osds.databinding.FragmentMerchandiseInfoBinding

class MerchandiseInfoFragment : Fragment() {

    private lateinit var binding: FragmentMerchandiseInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMerchandiseInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        merchandiseInfoFragment = null
    }

    companion object {
        private var merchandiseInfoFragment: Fragment? = null
        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(): Fragment? {
            merchandiseInfoFragment = MerchandiseInfoFragment()
            return merchandiseInfoFragment
        }
    }
}