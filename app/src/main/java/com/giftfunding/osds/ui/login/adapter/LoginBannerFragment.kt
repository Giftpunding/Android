package com.giftfunding.osds.ui.login.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftfunding.osds.databinding.ContentBannerBinding
class LoginBannerFragment : Fragment() {

    private val bannerImg by lazy { arguments?.getInt("img") }
    private lateinit var binding: ContentBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContentBannerBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.ivBanner.setImageResource(bannerImg!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentListFragment = null
    }

    companion object {
        private var fragmentListFragment: Fragment? = null

        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(): Fragment? {
            fragmentListFragment = LoginBannerFragment()
            return fragmentListFragment
        }
    }
}