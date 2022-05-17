package com.giftpunding.osds.ui.merchandise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giftpunding.osds.databinding.FragmentMerchandiseDetailInfoBinding

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
}