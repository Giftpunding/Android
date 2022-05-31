package com.giftpunding.osds.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterResponse
import com.giftpunding.osds.databinding.ItemSoughtAfterGiftBinding
import com.giftpunding.osds.util.addComma

class VerticalGiftItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var _binding: ItemSoughtAfterGiftBinding? = null
    private val binding: ItemSoughtAfterGiftBinding get() = _binding!!
    var addGiftEvent : () -> Unit = {}
    var name: String
        get() = binding.tvSoughtAfterMerchandiseName.text.toString()
        set(value) {
            binding.tvSoughtAfterMerchandiseName.text = value
        }

    var brand: String
        get() = binding.tvSoughtAfeterBrand.text.toString()
        set(value) {
            binding.tvSoughtAfeterBrand.text = value
        }

    init {
        _binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_sought_after_gift,
            this,
            false
        )
        addView(binding.root)
        binding.btnMerchandiseAdd.setOnClickListener(this)
    }

    fun dataNullCheck(data: SoughtAfterResponse?): Boolean {
        return data != null
    }

    fun setPrice(price: Int?): String {
        return addComma(price!!)
    }

    fun setMerchandiseImg(imgUrl: String?) {
        Glide.with(context)
            .load(imgUrl)
            .centerCrop()
            .into(binding.ivSoughtAfterImg)
    }

    override fun onClick(view: View?) {
        //선물 추가
        addGiftEvent()
    }
}