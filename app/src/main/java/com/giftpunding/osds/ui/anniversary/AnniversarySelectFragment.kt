package com.giftpunding.osds.ui.anniversary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.giftpunding.osds.databinding.FragmentAnniversarySelectBinding
import com.giftpunding.osds.ui.home.HomeActivity
import kotlinx.coroutines.launch

class AnniversarySelectFragment : Fragment() {

    private lateinit var binding: FragmentAnniversarySelectBinding
    private val viewModel: AnniversaryFragmentViewModel by viewModels()
    private lateinit var anniversary: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnniversarySelectBinding.inflate(inflater, container, false)
        init()
        initCalender()
        initEvent()
        initAnniversaryViewModel()
        return binding.root
    }

    fun init() {

    }

    private fun initCalender() {
        binding.npEventDatePicker.run {
            npMonth.minValue = minMonth
            npMonth.maxValue = maxMonth
            npDayOfMonth.minValue = minDay
        }

        initDayOfMonth(minMonth)
    }

    private fun initAnniversaryViewModel(){
        viewModel.anniversaryResponse.observe(viewLifecycleOwner){
            //옵저빙하여 변화가 있으면 실행
            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }
    }

    fun initEvent() {
        changeDayOfMonthMaxValue()

        binding.btnMovePage.setOnClickListener {
            val month = binding.npEventDatePicker.npMonth.value.toString()
            val day = binding.npEventDatePicker.npDayOfMonth.value.toString()
            // 해당 형태로 전달
            val anniversaryDay = (month + "월" + day +"일")

            Log.d("Test anniversary", "$anniversaryDay , $anniversary")
            viewModel.addAnniversary(anniversaryDay, anniversary)
        }

        // 서버에 영어 형태로 전달
        binding.rBtnBirthday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) setAnniversary("birthday")
        }

        binding.rBtnPregnancy.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) setAnniversary("pregnancy")
        }

        binding.rBtnHousewarming.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) setAnniversary("housewarming")
        }

        binding.rBtnMarry.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) setAnniversary("marry")
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

    private fun setAnniversary(anniversary: String){
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