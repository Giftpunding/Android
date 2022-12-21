package com.giftfunding.osds.ui.address


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.giftfunding.osds.R
import com.giftfunding.osds.base.BaseFragment
import com.giftfunding.osds.databinding.FragmentAddressDetailBinding
import com.giftfunding.osds.ui.address.model.AddressUiModel
import com.giftfunding.osds.util.*
import com.giftfunding.osds.util.keyboard.Keyboard
import com.giftfunding.osds.util.keyboard.KeyboardImpl


class AddressDetailFragment : BaseFragment<FragmentAddressDetailBinding>() {
    private val args: AddressDetailFragmentArgs by navArgs()
    private val viewModel: AddressDetailViewModel by viewModels()
    private var keyboard = KeyboardImpl()

    override fun layoutResId(): Int = R.layout.fragment_address_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args) {
            init(address)
        }

        initEvent()
        initObserverEvent()
        initKeyboardEvent()

    }

    private fun init(address: AddressUiModel) {
        binding.editAddressDetail.setFocusAndShowKeyboard(requireContext())

        binding.tvSearchKeyword.text = address.keywordAddress
        binding.tvAddress.text = address.addressName
        binding.tvAddressType.text = when (address.addressType) {
            "ROAD", "ROAD_ADDR" -> {
                getString(R.string.content_road_name)
            }
            else -> {
                getString(R.string.content_address_name)
            }
        }
    }

    override fun initEvent() {
        binding.editAddressDetail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(detailAddress: Editable?) {
                viewModel.addressDetailTextChanged(detailAddress.toString())
            }
        })

        binding.btnTextDelete.setOnClickListener {
            binding.editAddressDetail.text?.clear()
        }

        binding.btnComplete.setOnClickListener {
            navigate(AddressDetailFragmentDirections.actionAddressDetailFragmentToHomeFragment())
        }
    }

    override fun initObserverEvent() {
        // edittext에 적힌 텍스트 삭제 버튼
        viewModel.isVisibleDeleteButton.observe(viewLifecycleOwner) { isVisible ->
            if (isVisible) {
                binding.btnTextDelete.visibility = View.VISIBLE
            } else {
                binding.btnTextDelete.visibility = View.GONE
            }
        }

        //등록 버튼 색 변경
        viewModel.isExistText.observe(viewLifecycleOwner) { isExistText ->
            when (isExistText) {
                true -> {
                    updateEnabledButton()
                }
                false -> {
                    updateDisabledButton()
                }
            }
        }

        //버튼 활성화
        viewModel.isEnableButton.observe(viewLifecycleOwner) { isEnable ->
            binding.btnComplete.isEnabled = isEnable
        }
    }

    // 키보드 높이 구하기
    // editText focus 여부에 따라, 버튼 색 변경
    private fun initKeyboardEvent() {
        keyboard.init(binding.root.rootView, deviceHeight(requireContext()), object : Keyboard {
            override fun keyboardShow(height: Int) {
                Log.d(TAG, "keyboardShow")
                viewModel.setKeyboardHeight(height)
                viewModel.addressDetailTextChanged(binding.editAddressDetail.text.toString())
            }

            override fun keyboardHide(height: Int) {
                Log.d(TAG, "keyboardHide")

                viewModel.setKeyboardHeight(height)
                viewModel.addressDetailTextChanged(binding.editAddressDetail.text.toString())
            }
        })
        keyboard.onGlobalLayout()
    }

    // 입력받은 텍스트가 있을 때
    private fun updateEnabledButton() {
        // 입력받은 텍스트는 있고, 키보드가 올라왔을 떄
        if (viewModel.keyboardHeight != 0) {
            Log.d(TAG, "updateEnabledButton ... is ShowKeyboard...")
            updateCompleteButtonWhenIsShowKeyboard()
            binding.btnComplete.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.bg_rect_black_midnight_express_round0_stroke0,
                null
            )
        } else {
            Log.d(TAG, "updateEnabledButton is hideKeyboard...")
            updateCompleteButtonWhenIsHideKeyboard()
            binding.btnComplete.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.bg_rect_black_midnight_express_radius10_stroke1,
                null
            )
        }
    }

    //editText에 텍스트가 없을 때
    private fun updateDisabledButton() {
        // 입력받은 텍스트는 없고 키보드가 올라왔을 때
        if (viewModel.keyboardHeight != 0) {
            Log.d(TAG, "updateDisabledButton is ShowKeyboard...")
            updateCompleteButtonWhenIsShowKeyboard()
            binding.btnComplete.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.bg_rect_white_link_water_round0_stroke0,
                null
            )
        }
        // 입력받은 텍스트는 없고 키보드가 내려갔을 때
        else {
            Log.d(TAG, "updateDisabledButton is hideKeyboard...")
            updateCompleteButtonWhenIsHideKeyboard()
            binding.btnComplete.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.bg_rect_white_link_water_round10_stroke0,
                null
            )
        }
    }

    private fun updateCompleteButtonWhenIsShowKeyboard() {
        Log.d(TAG, "updateCompleteButtonWhenIsShowKeyboard ${viewModel.keyboardHeight}")
        val params = binding.btnComplete.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = viewModel.keyboardHeight
        params.marginStart = resources.getDimension(R.dimen.default_margin).toInt()
        params.marginEnd = resources.getDimension(R.dimen.default_margin).toInt()
        binding.btnComplete.layoutParams = params

    }

    private fun updateCompleteButtonWhenIsHideKeyboard() {
        Log.d(TAG, "updateCompleteButtonWhenIsHideKeyboard ${viewModel.keyboardHeight}")
        val params = binding.btnComplete.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = resources.getDimension(R.dimen.button_bottom_margin).toInt()
        params.marginStart = resources.getDimension(R.dimen.activity_horizontal_dimens).toInt()
        params.marginEnd = resources.getDimension(R.dimen.activity_horizontal_dimens).toInt()
        binding.btnComplete.layoutParams = params
    }

    override fun onDestroyView() {
        super.onDestroyView()
        keyboard.remove()
    }

    companion object {
        private const val TAG: String = "AddressDetailFragment"
    }
}