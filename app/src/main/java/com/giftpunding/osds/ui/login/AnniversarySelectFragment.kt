package com.giftpunding.osds.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.FragmentAnniversarySelectBinding
import com.giftpunding.osds.ui.login.adapter.AnniversaryListAdapter
import com.giftpunding.osds.ui.login.adapter.AnniversaryListAdapterDecoration

class AnniversarySelectFragment : Fragment() {

    private lateinit var binding: FragmentAnniversarySelectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnniversarySelectBinding.inflate(inflater, container, false)
        init()
        initEvent()
        return binding.root
    }

    fun init() {
        binding.npEventDatePicker.npMonth.maxValue = 12
        binding.npEventDatePicker.npMonth.minValue = 1
        binding.npEventDatePicker.npDayOfMonth.minValue = 1

        when (binding.npEventDatePicker.npMonth.value) {
            1, 3, 5, 7, 8, 10, 12 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 31
            2 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 29
            4, 6, 9, 11 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 30
        }
    }

    fun initEvent() {
        changeDayOfMonthMaxValue()
    }

    private fun changeDayOfMonthMaxValue() {
        binding.npEventDatePicker.npMonth.setOnValueChangedListener { _, _, newValue ->
            when (newValue) {
                1, 3, 5, 7, 8, 10, 12 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 31
                2 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 29
                4, 6, 9, 11 -> binding.npEventDatePicker.npDayOfMonth.maxValue = 30
            }
        }
    }
}