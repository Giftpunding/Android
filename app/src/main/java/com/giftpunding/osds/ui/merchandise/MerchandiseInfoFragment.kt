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
}