package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftfunding.osds.databinding.ItemBannerBinding

class HomeBannerFragment : Fragment(){
    private lateinit var binding: ItemBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemBannerBinding.inflate(inflater, container, false)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBannerFragment = null
    }

    companion object{
        private lateinit var homeBannerFragment : HomeBannerFragment

        fun getFragmentInstance() : Fragment{
            if(homeBannerFragment == null){
               homeBannerFragment = HomeBannerFragment()
            }

            return homeBannerFragment
        }
    }
}