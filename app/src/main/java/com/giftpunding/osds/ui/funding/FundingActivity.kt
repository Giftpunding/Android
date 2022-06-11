package com.giftpunding.osds.ui.funding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.giftpunding.osds.R
import com.giftpunding.osds.base.BaseActivity
import com.giftpunding.osds.databinding.ActivityFundingBinding
import com.giftpunding.osds.util.addComma

class FundingActivity : BaseActivity<ActivityFundingBinding>(ActivityFundingBinding::inflate), View.OnClickListener,
    RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener,
    TextView.OnEditorActionListener {

    private lateinit var messageTextWatcher: TextWatcher
    private lateinit var inputPriceTextWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun init() {
        //메시지 텍스트 개수 감지
        messageTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, nowTextSize: Int) {
                binding.tvMessageCount.text = "$nowTextSize/100"
                //텍스트 크기가 0이면 안보이게
                if (nowTextSize > 0) {
                    binding.tvMessageCount.visibility = View.VISIBLE
                } else {
                    binding.tvMessageCount.visibility = View.INVISIBLE
                }

                //100자 넘어감
                if (nowTextSize > 100) {

                } else {

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }

        //직접 가격 입력하는 EditText
        inputPriceTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                nowTextSize: Int,
                p2: Int,
                inputCount: Int
            ) {
                //만약 가격 텍스트가 있다면?
                if (inputCount > 0) {
                    //가격 전용 폰트(숫자)
                    binding.editInputPrice.typeface =
                        ResourcesCompat.getFont(this@FundingActivity, R.font.helveticanene_medium)
                    binding.editInputPrice.setTextSize(Dimension.SP, 16f)
                    binding.editInputPrice.setBackgroundResource(R.drawable.bg_rect_midnight_express_midnight_express_radius6_stroke1_padding_8_29_30_9)
                } else {
                    //가격 없는 폰트(hint 글자)
                    binding.editInputPrice.typeface =
                        ResourcesCompat.getFont(this@FundingActivity, R.font.notosanskr_medium)
                    binding.editInputPrice.setTextSize(Dimension.SP, 14f)
                }
            }

            override fun onTextChanged(text: CharSequence?, nowTextSize: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
    }

    override fun initEvent() {
        binding.apply {
            btnPurchase.setOnClickListener(this@FundingActivity)
            editMessage.addTextChangedListener(messageTextWatcher)
            editInputPrice.addTextChangedListener(inputPriceTextWatcher)
            editInputPrice.onFocusChangeListener = this@FundingActivity
            editInputPrice.setOnEditorActionListener(this@FundingActivity)
            paymentType.setOnCheckedChangeListener(this@FundingActivity)
            priceType.setOnCheckedChangeListener(this@FundingActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnPurchase -> {
                //구매
            }
            binding.editInputPrice -> {
                //가격 직접 입력
                binding.priceType.clearCheck()

            }
        }
    }

    override fun onCheckedChanged(view: RadioGroup?, viewId: Int) {
        when (view) {
            //각 버튼을 누르면 EditText는 초기화
            binding.priceType -> {
                when (viewId) {
                    R.id.rb_price_all_balance -> {
                        hideKeyboard(binding.editInputPrice)
                        binding.editInputPrice.clearFocus()
                    }
                    R.id.rb_price_ten_thousand -> {
                        hideKeyboard(binding.editInputPrice)
                        binding.editInputPrice.clearFocus()
                    }
                    R.id.rb_price_five_thousand -> {
                        hideKeyboard(binding.editInputPrice)
                        binding.editInputPrice.clearFocus()
                    }
                }
                binding.editInputPrice.hint = resources.getString(R.string.category_direct_input)
                changeInputPrice(false)
            }

            //결제 방식
            binding.paymentType -> {
                when (viewId) {
                    R.id.rb_payment_card -> {

                    }
                    R.id.rb_payment_phone -> {

                    }
                }
            }
        }
    }

    override fun onFocusChange(view: View?, check: Boolean) {
        when (view) {
            binding.editInputPrice -> {
                //포커스 됨
                if (check) {
                    //라디오 버튼 해제
                    binding.priceType.clearCheck()
                    binding.editInputPrice.requestFocus()
                    revealKeyboard(binding.editInputPrice)
                    changeInputPrice(true)
                }
                //포커스 해제
                else {
                    //데이터가 존재함
                    if (binding.editInputPrice.text.toString() != "") {
                        binding.editInputPrice.setText(
                            addComma(
                                binding.editInputPrice.text.toString().toInt()
                            )
                        )
                    } else {
                        changeInputPrice(false)
                    }
                }
            }
        }
    }

    //EditInputPrice의 변화
    private fun changeInputPrice(check: Boolean) {
        binding.editInputPrice.apply {
            //선택됨
            if (check) {
                if (this.text.toString().isNotEmpty()) {
                    //숫자
                    setBackgroundResource(R.drawable.bg_rect_midnight_express_midnight_express_radius6_stroke1_padding_11_22_23_9)
                } else {
                    //글자 없음(힌트만)
                    setBackgroundResource(R.drawable.bg_rect_midnight_express_midnight_express_radius6_stroke1_padding_8_29_30_9)
                }
                setTextColor(ResourcesCompat.getColor(resources, R.color.solitude_2, null))
            }
            //선택 초기화
            else {
                setBackgroundResource(R.drawable.bg_rect_link_water_hawkes_blue_radius6_stroke1_padding_11_22_23_9)
                setTextColor(ResourcesCompat.getColor(resources, R.color.raven, null))
                typeface = ResourcesCompat.getFont(this@FundingActivity, R.font.notosanskr_medium)
                setText("")
                hint = resources.getString(R.string.category_direct_input)
            }
        }
    }

    override fun onEditorAction(view: TextView?, imeOption: Int, p2: KeyEvent?): Boolean {
        if (imeOption == EditorInfo.IME_ACTION_DONE && binding.editInputPrice.text.toString() != "") {
            //숫자가 있는 경우
            changeInputPrice(true)
            hideKeyboard(binding.editInputPrice)
            binding.editInputPrice.clearFocus()
        }
        return true
    }
}