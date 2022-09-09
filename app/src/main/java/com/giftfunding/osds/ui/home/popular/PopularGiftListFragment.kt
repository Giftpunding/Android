package com.giftfunding.osds.ui.home.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftfunding.osds.data.response.home.popualrGift.PopularGiftResponse
import com.giftfunding.osds.databinding.FragmentPopularGiftListBinding
import java.lang.IndexOutOfBoundsException
import java.util.ArrayList

class PopularGiftListFragment : Fragment() {

    private lateinit var binding: FragmentPopularGiftListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val list: ArrayList<PopularGiftResponse>? = arguments?.getParcelableArrayList("listData")

        val addGift = {
            //선물 추가 이벤트
        }

        binding = FragmentPopularGiftListBinding.inflate(inflater, container, false)
        binding.apply {
            if (list?.size != 0) {
                itemFirstGift.apply {
                    try {
                        setMerchandiseImg(list?.get(0)?.img)
                        setPrice(list?.get(0)?.price)
                        name = list?.get(0)?.name.toString()
                        brand = list?.get(0)?.brand.toString()
                        addGiftEvent = addGift
                    } catch (error: IndexOutOfBoundsException) {
                        itemSecondGift.visibility = View.INVISIBLE
                    }
                }
                itemSecondGift.apply {
                    try {
                        setMerchandiseImg(list?.get(1)?.img)
                        setPrice(list?.get(1)?.price)
                        name = list?.get(1)?.name.toString()
                        brand = list?.get(1)?.brand.toString()
                        addGiftEvent = addGift
                    } catch (error: IndexOutOfBoundsException) {
                        itemSecondGift.visibility = View.INVISIBLE
                    }
                }
                itemThirdGift.apply {
                    try {
                        setMerchandiseImg(list?.get(2)?.img)
                        setPrice(list?.get(2)?.price)
                        name = list?.get(2)?.name.toString()
                        brand = list?.get(2)?.brand.toString()
                        addGiftEvent = addGift
                    } catch (error: IndexOutOfBoundsException) {
                        itemThirdGift.visibility = View.INVISIBLE
                    }
                }
                itemFourthGift.apply {
                    try {
                        setMerchandiseImg(list?.get(3)?.img)
                        setPrice(list?.get(3)?.price)
                        name = list?.get(3)?.name.toString()
                        brand = list?.get(3)?.brand.toString()
                        addGiftEvent = addGift
                    } catch (error: IndexOutOfBoundsException) {
                        itemFourthGift.visibility = View.INVISIBLE
                    }
                }
            }
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
            soughtAfterListFragment = PopularGiftListFragment()
            return soughtAfterListFragment
        }
    }
}
