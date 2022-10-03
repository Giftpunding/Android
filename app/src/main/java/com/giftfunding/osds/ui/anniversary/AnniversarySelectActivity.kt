package com.giftfunding.osds.ui.anniversary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import com.giftfunding.osds.base.BaseActivity
import com.giftfunding.osds.databinding.ActivityAnniversarySelectBinding
import com.giftfunding.osds.enum.BackButton
import com.giftfunding.osds.enum.ToolbarType
import com.giftfunding.osds.enum.VisibleState
import com.giftfunding.osds.ui.address.AddressActivity

class AnniversarySelectActivity :
    BaseActivity<ActivityAnniversarySelectBinding>(ActivityAnniversarySelectBinding::inflate) {

    private val viewModel: AnniversarySelectViewModel by viewModels()
    private lateinit var anniversary: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initEvent()

    }

    override fun init() {
        setToolbarType(ToolbarType.NORMAL)
        setBackButtonVisible(VisibleState.VISIBLE)
        setBackButton(BackButton.BACK)
        setCloseButton(VisibleState.INVISIBLE)
        setTitle("타이틀")

        initCalender()
        initAnniversaryViewModel()
    }

    override fun initEvent() {
        binding.tvBirthday.setOnClickListener {
            setDatePickerView(binding.contentAnniversarySelect.rBtnBirthday)
        }

        binding.tvHousewarming.setOnClickListener {
            setDatePickerView(binding.contentAnniversarySelect.rBtnHousewarming)
        }

        binding.tvWedding.setOnClickListener {
            setDatePickerView(binding.contentAnniversarySelect.rBtnMarry)
        }

        binding.tvPregnancy.setOnClickListener {
            setDatePickerView(binding.contentAnniversarySelect.rBtnPregnancy)
        }

        changeDayOfMonthMaxValue()

        binding.contentAnniversarySelect.btnMovePage.setOnClickListener {
            val month = binding.contentAnniversarySelect.npEventDatePicker.npMonth.value.toString()
            val day = binding.contentAnniversarySelect.npEventDatePicker.npDayOfMonth.value.toString()
            // 해당 형태로 전달
            val anniversaryDay = (month + "월" + day + "일")

            Log.d("Test anniversary", "$anniversaryDay , $anniversary")
            startActivity(Intent(this, AddressActivity::class.java))

        }

        // 서버에 영어 형태로 전달
        binding.contentAnniversarySelect.rBtnBirthday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary("birthday")
        }

        binding.contentAnniversarySelect.rBtnPregnancy.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary("pregnancy")
        }

        binding.contentAnniversarySelect.rBtnHousewarming.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary("housewarming")
        }

        binding.contentAnniversarySelect.rBtnMarry.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary("marry")
        }
        binding.contentAnniversarySelect.rBtnUserInput.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) setAnniversary(anniversary)
        }
    }

    private fun setDatePickerView(view: RadioButton) {
        binding.contentAnniversarySelect.clContentAnniversarySelect.visibility = View.VISIBLE

        view.viewTreeObserver.addOnGlobalLayoutListener {
            binding.contentAnniversarySelect.svEventCategory.scrollTo(view.left, 0)
        }
        view.isChecked = true
    }

    private fun initCalender() {
        binding.contentAnniversarySelect.npEventDatePicker.run {
            npMonth.minValue = minMonth
            npMonth.maxValue = maxMonth
            npDayOfMonth.minValue = minDay
        }

        initDayOfMonth(minMonth)
    }

    private fun initAnniversaryViewModel() {
        //옵저빙하여 변화가 있으면 실행
        viewModel.anniversaryResponse.observe(this) {
            startActivity(Intent(this, AddressActivity::class.java))
        }
    }

    private fun changeDayOfMonthMaxValue() {
        binding.contentAnniversarySelect.npEventDatePicker.npMonth.setOnValueChangedListener { _, _, selectMonth ->
            initDayOfMonth(selectMonth)
        }
    }

    private fun initDayOfMonth(month: Int) {
        when (month) {
            1, 3, 5, 7, 8, 10, 12 ->
                binding.contentAnniversarySelect.npEventDatePicker.npDayOfMonth.maxValue =
                    oddMonthMaxDay
            2 -> binding.contentAnniversarySelect.npEventDatePicker.npDayOfMonth.maxValue =
                FebruaryMonthMaxDay
            4, 6, 9, 11 -> binding.contentAnniversarySelect.npEventDatePicker.npDayOfMonth.maxValue =
                evenMonthMaxDay
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