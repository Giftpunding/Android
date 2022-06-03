package com.giftpunding.osds.component

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.giftpunding.osds.R
import com.giftpunding.osds.databinding.ItemPopularGiftBinding
import com.giftpunding.osds.databinding.ItemPriceCategoryBinding
import com.giftpunding.osds.util.addComma

class PriceCategoryItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var _binding: ItemPriceCategoryBinding? = null
    private val binding: ItemPriceCategoryBinding get() = _binding!!

    init {
        _binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_price_category,
            this,
            false
        )
        addView(binding.root)
    }

    fun setCategoryName(name: String) {
        binding.tvPriceCategory.text = name
    }

    fun setFirstSelectItem() {
        binding.tvPriceCategory.apply {
            setTextColor(
                ResourcesCompat.getColor(
                    context.resources,
                    R.color.midnight_express,
                    null
                )
            )
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_bold)
        }
    }

    fun clickCategory(
        firstLeftItem: PriceCategoryItem,
        secondLeftItem: PriceCategoryItem,
        thirdLeftItem: PriceCategoryItem,
        fourthLeftItem: PriceCategoryItem,
        clickItem: PriceCategoryItem
    ) {
        firstLeftItem.binding.tvPriceCategory.apply {
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.echo_blue_2, null))
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
        }

        secondLeftItem.binding.tvPriceCategory.apply {
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.echo_blue_2, null))
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
        }

        thirdLeftItem.binding.tvPriceCategory.apply {
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.echo_blue_2, null))
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
        }

        fourthLeftItem.binding.tvPriceCategory.apply {
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.echo_blue_2, null))
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
        }

        clickItem.binding.tvPriceCategory.apply {
            setTextColor(ResourcesCompat.getColor(context.resources, R.color.echo_blue_2, null))
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_regular)
        }

        clickItem.binding.tvPriceCategory.apply {
            setTextColor(
                ResourcesCompat.getColor(
                    context.resources,
                    R.color.midnight_express,
                    null
                )
            )
            typeface = ResourcesCompat.getFont(context, R.font.notosanskr_bold)
        }
    }
}

