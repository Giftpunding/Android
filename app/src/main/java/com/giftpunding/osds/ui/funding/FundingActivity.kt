package com.giftpunding.osds.ui.funding

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ActivityFundingBinding

class FundingActivity : AppCompatActivity(), View.OnClickListener, TextWatcher,
    RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityFundingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFundingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initEvent()
    }

    private fun init() {

    }

    private fun initEvent() {
        binding.apply {
            btnPurchase.setOnClickListener(this@FundingActivity)
            editMessage.addTextChangedListener(this@FundingActivity)
            rgPaymentType.setOnCheckedChangeListener(this@FundingActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnPurchase -> {
                //구매
            }
        }
    }

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(text: CharSequence?, nowTextSize: Int, p2: Int, p3: Int) {
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

    override fun onCheckedChanged(view: RadioGroup?, viewId: Int) {
        when (viewId) {
            R.id.rb_payment_card -> {
                binding.rbPaymentCard.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                binding.rbPaymentPhone.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.midnight_express,
                        null
                    )
                )
            }
            R.id.rb_payment_phone -> {
                binding.rbPaymentPhone.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )
                binding.rbPaymentCard.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.midnight_express,
                        null
                    )
                )
            }
        }
    }
}