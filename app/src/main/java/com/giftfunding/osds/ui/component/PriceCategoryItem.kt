package com.giftfunding.osds.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.giftfunding.osds.R
import com.giftfunding.osds.databinding.ItemPriceCategoryBinding

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

    //카테고리 이름 지정
    fun setCategoryName(name: String) {
        binding.tvPriceCategory.text = name
    }

    //첫 아이템을 지정
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

    //카테고리 클릭
    //마지막 매개변수가 클릭되는 아이템
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

