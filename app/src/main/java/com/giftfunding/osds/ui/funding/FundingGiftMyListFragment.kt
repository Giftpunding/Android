package com.giftfunding.osds.ui.funding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftfunding.osds.data.repository.remote.datasource.dto.funding.FundingCompleteResponse
import com.giftfunding.osds.databinding.FragmentFundingListBinding
import com.giftfunding.osds.ui.funding.adapter.FundingListMyItemAdapter

class FundingGiftMyListFragment : Fragment() {
    private lateinit var binding: FragmentFundingListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFundingListBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init() {
        val tempList = listOf<FundingCompleteResponse>(
            FundingCompleteResponse(
                "img1",
                "brand1",
                "name1",
                13000
            )
        )

        binding.apply {
            rvFundingList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvFundingList.adapter = FundingListMyItemAdapter(requireContext(), tempList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentListFragment = null
    }

    companion object {
        private var fragmentListFragment: Fragment? = null

        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(): Fragment? {
            fragmentListFragment = FundingGiftMyListFragment()
            return fragmentListFragment
        }
    }
}