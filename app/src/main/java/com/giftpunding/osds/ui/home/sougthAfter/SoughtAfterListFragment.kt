package com.giftpunding.osds.ui.home.sougthAfter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterResponse
import com.giftpunding.osds.databinding.FragmentSoughtAfterListBinding
import com.giftpunding.osds.ui.home.sougthAfter.adapter.SoughtAfterGiftListAdapter
import com.giftpunding.osds.util.GridRecyclerViewDeco
import java.util.ArrayList

class SoughtAfterListFragment : Fragment() {

    private lateinit var binding: FragmentSoughtAfterListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val list : ArrayList<SoughtAfterResponse>? =  arguments?.getParcelableArrayList("listData")

        binding = FragmentSoughtAfterListBinding.inflate(inflater, container, false)
        binding.apply {
            rvSoughtAfterGift.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter = SoughtAfterGiftListAdapter(requireContext())
            rvSoughtAfterGift.adapter = adapter
            rvSoughtAfterGift.addItemDecoration(GridRecyclerViewDeco(15))

            //번들로 받은 데이터 넣어주기기
           adapter.addItemList(list)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        soughtAfterListFragment = null
    }

    companion object {
        private var soughtAfterListFragment: Fragment? = null

        //생성자를 통해 데이터를 넘겨받고 bundle로 처리
        fun getFragmentInstance(): Fragment? {
            soughtAfterListFragment = SoughtAfterListFragment()
            return soughtAfterListFragment
        }
    }
}
