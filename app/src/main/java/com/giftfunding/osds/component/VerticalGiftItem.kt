package com.giftfunding.osds.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.giftfunding.osds.R
import com.giftfunding.osds.databinding.ItemPopularGiftBinding
import com.giftfunding.osds.util.addComma

//JvmOverloads 는 생성자를 모두 만들게함
class VerticalGiftItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var _binding: ItemPopularGiftBinding? = null
    private val binding: ItemPopularGiftBinding get() = _binding!!
    var addGiftEvent : () -> Unit = {}
    var name: String
        get() = binding.tvPopularGiftMerchandiseName.text.toString()
        set(value) {
            binding.tvPopularGiftMerchandiseName.text = value
        }

    var brand: String
        get() = binding.tvPopularGiftBrand.text.toString()
        set(value) {
            binding.tvPopularGiftBrand.text = value
        }

    init {
        _binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_popular_gift,
            this,
            false
        )
        addView(binding.root)
        binding.btnMerchandiseAdd.setOnClickListener(this)
    }

    fun setPrice(price: Int?): String {
        return addComma(price!!)
    }

    fun setMerchandiseImg(imgUrl: String?) {
        Glide.with(context)
            .load(imgUrl)
            .centerCrop()
            .into(binding.ivPopularGiftImg)
    }

    override fun onClick(view: View?) {
        //선물 추가
        addGiftEvent()
    }
}