package com.giftpunding.osds.ui.anniversary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.giftpunding.osds.databinding.FragmentAnniversarySelectBinding
import kotlinx.coroutines.launch

class AnniversarySelectFragment : Fragment() {

    private lateinit var binding: FragmentAnniversarySelectBinding
    private val viewModel: AnniversaryFragmentViewModel by viewModels()
    private lateinit var anniversary: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnniversarySelectBinding.inflate(inflater, container, false)
        init()
        initCalender()
        initEvent()
        return binding.root
    }

    fun init() {
        when (requireArguments().getString(AnniversarySelectActivity.ANNIVERSARY_TYPE)) {
            AnniversarySelectActivity.BIRTHDAY -> {
                binding.rBtnBirthday.isChecked = true
            }
            AnniversarySelectActivity.PREGNANCY -> {
                binding.rBtnPregnancy.isChecked = true
            }
            AnniversarySelectActivity.HOUSEWARMING -> {
                binding.rBtnHousewarming.isChecked = true
            }
            AnniversarySelectActivity.WEDDING -> {
                binding.rBtnMarry.isChecked = true
            }
        }
    }

    private fun initCalender() {
        binding.npEventDatePicker.run {
            npMonth.minValue = minMonth
            npMonth.maxValue = maxMonth
            npDayOfMonth.minValue = minDay
        }

        initDayOfMonth(minMonth)
    }

    fun initEvent() {
        changeDayOfMonthMaxValue()

        binding.btnMovePage.setOnClickListener {
            val month = binding.npEventDatePicker.npMonth.value.toString()
            val day = binding.npEventDatePicker.npDayOfMonth.value.toString()
            //서버에서 데이터 관해서 이야기 필요
            // 1월1일로 받을지 , 1, 1 처럼 월 일 따로 받을지 논의해야하는 사항
            val anniversaryDay = (month + "월" + day + "일")
//            viewModel.addAnniversary(anniversaryDay, anniversary)
        }

        // 서버에 영어로 보낼 지 정해야함
        binding.rBtnBirthday.setOnClickListener {
            setAnniversary("birthday")
        }

        binding.rBtnPregnancy.setOnClickListener {
            setAnniversary("pregnancy")
        }

        binding.rBtnHousewarming.setOnClickListener {
            setAnniversary("housewarming")
        }

        binding.rBtnMarry.setOnClickListener {
            setAnniversary("marry")
        }
    }

    private fun changeDayOfMonthMaxValue() {
        binding.npEventDatePicker.npMonth.setOnValueChangedListener { _, _, selectMonth ->
            initDayOfMonth(selectMonth)
        }
    }

    private fun initDayOfMonth(month: Int) {
        when (month) {
            1, 3, 5, 7, 8, 10, 12 ->
                binding.npEventDatePicker.npDayOfMonth.maxValue = oddMonthMaxDay
            2 -> binding.npEventDatePicker.npDayOfMonth.maxValue = FebruaryMonthMaxDay
            4, 6, 9, 11 -> binding.npEventDatePicker.npDayOfMonth.maxValue = evenMonthMaxDay
        }
    }

    private fun setAnniversary(anniversary: String) {
        this.anniversary = anniversary
    }

    companion object {
        const val minMonth = 1
        const val maxMonth = 12
        const val minDay = 1
        const val evenMonthMaxDay = 30
        const val oddMonthMaxDay = 31
        const val FebruaryMonthMaxDay = 28
    }
}