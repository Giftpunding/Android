package com.giftfunding.osds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftfunding.osds.databinding.ItemBannerBinding

class HomeBannerFragment : Fragment() {

    private lateinit var binding: ItemBannerBinding
    private val bannerImg by lazy { arguments?.getInt("img") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBanner.setImageResource(bannerImg!!)
    }

    companion object {

        fun newInstance(bannerItems: Int): Fragment {
            val homeBannerFragment = HomeBannerFragment().apply {
               arguments = Bundle().apply {
                   putInt("img", bannerItems)
               }
            }
            return homeBannerFragment
        }
    }
}