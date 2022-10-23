package com.giftfunding.osds.ui.anniversary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.giftfunding.osds.*
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAnniversarySelectDateBinding
import com.giftfunding.osds.enum.AnniversaryType
import com.giftfunding.osds.ui.address.AddressActivity
import com.giftfunding.osds.ui.anniversary.viewmodel.AnniversaryViewModel
import com.giftfunding.osds.util.initDay
import com.giftfunding.osds.util.initMonth
import com.giftfunding.osds.util.setDayOfMonth


class AnniversaryDateSelectFragment : BaseFragment<FragmentAnniversarySelectDateBinding>() {

    override fun layoutResId(): Int = R.layout.fragment_anniversary_select_date

    private val args: AnniversaryDateSelectFragmentArgs by navArgs()
    private val anniversaryViewModel: AnniversaryViewModel by viewModels()

    private lateinit var anniversary: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args) {
            initArguments(anniversaryType, userInput)
        }

        initEvent()
        initObserverEvent()
        initCalender()
    }


    //args로 받은 기념일 타입 가져오기
    private fun initArguments(anniversary: AnniversaryType, userInput: String) {
        setAnniversary(anniversary, userInput)
        setButtonState(anniversary, userInput)
    }

    // 기념일
    private fun setAnniversary(type: AnniversaryType, userInput: String) {
        when (type) {
            AnniversaryType.BIRTHDAY -> {
                anniversary = getString(R.string.category_anniversary_birthday_type)
            }
            AnniversaryType.PREGNANCY -> {
                anniversary = getString(R.string.category_anniversary_pregnancy_type)
            }
            AnniversaryType.HOUSEWARMING -> {
                anniversary = getString(R.string.category_anniversary_housewarming_type)
            }
            AnniversaryType.WEDDING -> {
                anniversary = getString(R.string.category_anniversary_wedding_type)
            }
            AnniversaryType.USER_INPUT -> {
                anniversary = userInput
            }
        }
    }

    // 버튼 상태 바꾸기
    private fun setButtonState(anniversary: AnniversaryType, userInput: String) {
        when (anniversary) {
            AnniversaryType.BIRTHDAY -> {
                updateAnniversaryView(binding.rBtnBirthday)
            }
            AnniversaryType.PREGNANCY -> {
                updateAnniversaryView(binding.rBtnPregnancy)
            }
            AnniversaryType.HOUSEWARMING -> {
                updateAnniversaryView(binding.rBtnHousewarming)
            }
            AnniversaryType.WEDDING -> {
                updateAnniversaryView(binding.rBtnMarry)
            }
            AnniversaryType.USER_INPUT -> {
                binding.rBtnUserInput.text = userInput
                updateAnniversaryView(binding.rBtnUserInput)
            }
        }
    }

    private fun updateAnniversaryView(radioButton: RadioButton) {
        radioButton.isChecked = true
        binding.svEventCategory.smoothScrollTo(radioButton.left, 0)
    }

    override fun initEvent() {
        binding.npEventDatePicker.npMonth.setOnValueChangedListener { _, _, selectMonth ->
            binding.npEventDatePicker.npDayOfMonth.setDayOfMonth(selectMonth)
        }

        binding.btnRegisterAnniversary.setOnClickListener {
            val month = binding.npEventDatePicker.npMonth.value.toString()
            val day = binding.npEventDatePicker.npDayOfMonth.value.toString()
            // 해당 형태로 전달
            val anniversaryDay = (month + "월" + day + "일")
            addRegisterAnniversary(anniversaryDay)
        }

        //기념일 라디오 버튼 이벤트 추가
        addAnniversaryTypeButtonEvent()
    }

    private fun addAnniversaryTypeButtonEvent() {
        binding.rBtnBirthday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAnniversary(AnniversaryType.BIRTHDAY, "")
                updateAnniversaryView(binding.rBtnBirthday)
            }
        }
        binding.rBtnPregnancy.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAnniversary(AnniversaryType.PREGNANCY, "")
                updateAnniversaryView(binding.rBtnPregnancy)
            }

        }
        binding.rBtnHousewarming.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAnniversary(AnniversaryType.HOUSEWARMING, "")
                updateAnniversaryView(binding.rBtnHousewarming)
            }
        }
        binding.rBtnMarry.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAnniversary(AnniversaryType.WEDDING, "")
                updateAnniversaryView(binding.rBtnMarry)
            }
        }

        binding.rBtnUserInput.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAnniversary(AnniversaryType.USER_INPUT, binding.rBtnUserInput.text.toString())
                updateAnniversaryView(binding.rBtnUserInput)
            }
        }
    }

    override fun initObserverEvent() {
        //옵저빙하여 변화가 있으면 실행
        anniversaryViewModel.anniversaryResponse.observe(viewLifecycleOwner) {
            startActivity(Intent(requireContext(), AddressActivity::class.java))
        }
    }

    //date picker 초기화
    private fun initCalender() {
        binding.npEventDatePicker.apply {
            npMonth.initMonth()
            npDayOfMonth.initDay()
            npDayOfMonth.setDayOfMonth(MIN_MONTH)
        }
    }

    private fun addRegisterAnniversary(anniversaryDay: String) {
        anniversaryViewModel.addAnniversary(anniversaryDay, anniversary)
    }

    companion object {
        private const val MIN_MONTH = 1
    }
}