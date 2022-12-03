package com.giftfunding.osds.ui.anniversary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.giftfunding.osds.*
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.base.ViewState
import com.giftfunding.osds.databinding.FragmentAnniversarySelectDateBinding
import com.giftfunding.osds.ui.enum.AnniversaryType
import com.giftfunding.osds.util.*


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
        // 위치 조절 하는건 추후에 다시 조절해야할듯... 아이디어가 떠오르지않음
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
            // 임시로 년도 입력 해당 형태로 전달
            val date = buildAnniversaryDate(month, day)
            addRegisterAnniversary(date)
        }

        //기념일 라디오 버튼 이벤트 추가
        addAnniversaryTypeButtonEvent()
    }

    private fun buildAnniversaryDate(month: String, day: String): String {
        // 현재 서버에서 년도도 입력받게 되어있는 상태라 default 값으로 1900 주입
        return "1900" + month.convertMonth() + day.convertDay()
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
        anniversaryViewModel.anniversaryResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    //로딩 다이얼로그 show
                    Log.d("TEST!!!!", "Loading")

                }
                is ViewState.Success -> {
                    navigate(
                        AnniversaryDateSelectFragmentDirections
                            .actionAnniversaryDateSelectFragmentToAddressFragment()
                    )
                }
                is ViewState.Error -> {
                    // 로딩 다이얼로그 dismiss, 서버에 message 전달해달라고해야할듯
//                    showSnackBar(requireView(), response.message?: "")
                    navigate(
                        AnniversaryDateSelectFragmentDirections
                            .actionAnniversaryDateSelectFragmentToAddressFragment()
                    )
                }
            }
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