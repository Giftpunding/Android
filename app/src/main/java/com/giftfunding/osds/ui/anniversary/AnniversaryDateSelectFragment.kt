package com.giftfunding.osds.ui.anniversary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAnniversarySelectBinding
import com.giftfunding.osds.databinding.FragmentAnniversarySelectDateBinding
import com.giftfunding.osds.enum.AnniversaryType
import com.giftfunding.osds.ui.address.AddressActivity
import com.giftfunding.osds.ui.anniversary.viewmodel.AnniversaryViewModel

class AnniversaryDateSelectFragment : BaseFragment<FragmentAnniversarySelectDateBinding>() {

    private val args: AnniversaryDateSelectFragmentArgs by navArgs()
    private val anniversaryViewModel: AnniversaryViewModel by viewModels()

    private lateinit var anniversaryType: String
    private lateinit var saveUserInput: String

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnniversarySelectDateBinding {
        return FragmentAnniversarySelectDateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args) {
            setAnniversaryType(anniversaryType, userInput)
        }
        initCalender()
        observeAnniversaryViewModel()
        changeDayOfMonthMaxValue()

        addRegisterAnniversaryEvent()
        addAnniversaryTypeButtonEvent()
    }

    private fun setAnniversaryType(type: AnniversaryType, userInput: String) {
        when (type) {
            AnniversaryType.BIRTHDAY -> {
                anniversaryType = getString(R.string.category_anniversary_birthday_type)
                setSelectedAnniversary(binding.rBtnBirthday)
            }
            AnniversaryType.PREGNANCY -> {
                anniversaryType = getString(R.string.category_anniversary_pregnancy_type)
                setSelectedAnniversary(binding.rBtnPregnancy)
            }
            AnniversaryType.HOUSEWARMING -> {
                anniversaryType = getString(R.string.category_anniversary_housewarming_type)
                setSelectedAnniversary(binding.rBtnHousewarming)
            }
            AnniversaryType.WEDDING -> {
                anniversaryType = getString(R.string.category_anniversary_wedding_type)
                setSelectedAnniversary(binding.rBtnMarry)
            }
            AnniversaryType.USER_INPUT -> {
                anniversaryType = userInput
                saveUserInput = userInput
                binding.rBtnUserInput.text = userInput
                setSelectedAnniversary(binding.rBtnUserInput)
            }
        }
    }

    private fun setSelectedAnniversary(radioButton: RadioButton) {
        radioButton.viewTreeObserver.addOnGlobalLayoutListener {
            binding.svEventCategory.scrollTo(radioButton.left, 0)
        }
        radioButton.isChecked = true
    }

    private fun initCalender() {
        binding.npEventDatePicker.apply {
            npMonth.minValue = MIN_MONTH
            npMonth.maxValue = MAX_MONTH
            npDayOfMonth.minValue = MIN_DAY
        }
        initDayOfMonth(MIN_MONTH)
    }

    private fun observeAnniversaryViewModel() {
        //옵저빙하여 변화가 있으면 실행
        anniversaryViewModel.anniversaryResponse.observe(viewLifecycleOwner) {
            startActivity(Intent(requireContext(), AddressActivity::class.java))
        }
    }


    private fun addRegisterAnniversaryEvent() {
        binding.btnRegisterAnniversary.setOnClickListener {
            val month = binding.npEventDatePicker.npMonth.value.toString()
            val day = binding.npEventDatePicker.npDayOfMonth.value.toString()
            // 해당 형태로 전달
            val anniversaryDay = (month + "월" + day + "일")
            anniversaryViewModel.addAnniversary(anniversaryDay, anniversaryType)
        }
    }

    private fun addAnniversaryTypeButtonEvent() {
        binding.rBtnBirthday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(AnniversaryType.BIRTHDAY)
        }
        binding.rBtnPregnancy.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(AnniversaryType.PREGNANCY)
        }
        binding.rBtnHousewarming.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(AnniversaryType.HOUSEWARMING)
        }
        binding.rBtnMarry.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(AnniversaryType.WEDDING)
        }
        binding.rBtnUserInput.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(AnniversaryType.USER_INPUT)
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
                binding.npEventDatePicker.npDayOfMonth.maxValue = ODD_MONTH_MAX_DAY
            2 -> binding.npEventDatePicker.npDayOfMonth.maxValue = FEBRUARY_MONTH_MAX_DAY
            4, 6, 9, 11 -> binding.npEventDatePicker.npDayOfMonth.maxValue = EVEN_MONTH_MAX_DAY
        }
    }

    private fun setAnniversary(type: AnniversaryType) {
        when (type) {
            AnniversaryType.BIRTHDAY -> {
                anniversaryType = getString(R.string.category_anniversary_birthday_type)
            }
            AnniversaryType.PREGNANCY -> {
                anniversaryType = getString(R.string.category_anniversary_pregnancy_type)
            }
            AnniversaryType.HOUSEWARMING -> {
                anniversaryType = getString(R.string.category_anniversary_housewarming_type)
            }
            AnniversaryType.WEDDING -> {
                anniversaryType = getString(R.string.category_anniversary_wedding_type)
            }
            AnniversaryType.USER_INPUT -> {
                anniversaryType = getString(R.string.category_anniversary_birthday_type)
            }
        }
    }

    companion object {
        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val MIN_DAY = 1
        private const val EVEN_MONTH_MAX_DAY = 30
        private const val ODD_MONTH_MAX_DAY = 31
        private const val FEBRUARY_MONTH_MAX_DAY = 28
    }
}