package com.giftpunding.osds.ui.home.sougthAfter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftpunding.osds.R
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterCategoryResponse
import com.giftpunding.osds.data.response.home.soughtAfter.SoughtAfterResponse
import com.giftpunding.osds.databinding.ItemSoughtAfterGiftCategoryBinding

class SoughtAfterGiftCategoryAdapter(val context: Context) :
    RecyclerView.Adapter<SoughtAfterGiftCategoryAdapter.SoughtAfterGiftCategoryHolder>() {
    inner class SoughtAfterGiftCategoryHolder(val binding: ItemSoughtAfterGiftCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    val list = mutableListOf<SoughtAfterCategoryResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SoughtAfterGiftCategoryHolder {
        val view = ItemSoughtAfterGiftCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SoughtAfterGiftCategoryHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: SoughtAfterGiftCategoryHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener {
                //boolean 값으로 클릭의 여부를 파악
                checkChange(position)
            }
            with(list[position]) {
                //boolean 값에 따른 view 변경
                if (check) {
                    binding.ivSoughtAfterCategoryImg.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.bg_oval_trans100_white_radius360_stroke0,
                        null
                    )
                    binding.lyMain.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.bg_rect_midnight_express_midnight_express_radius22_stroke1,
                        null
                    )
                    binding.tvTvSoughtAfterCategory.setTextColor(
                        ResourcesCompat.getColor(
                            context.resources,
                            R.color.white,
                            null
                        )
                    )
                } else {
                    binding.ivSoughtAfterCategoryImg.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.bg_oval_trans100_white_smoke_radius360_stroke0,
                        null
                    )
                    binding.lyMain.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.bg_rect_hawkes_blue_white_radius22_stroke1,
                        null
                    )
                    binding.tvTvSoughtAfterCategory.setTextColor(
                        ResourcesCompat.getColor(
                            context.resources,
                            R.color.raven,
                            null
                        )
                    )
                }
                //전체 내용은 통일
                Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(binding.ivSoughtAfterCategoryImg)

                binding.tvTvSoughtAfterCategory.text = category
            }
        }
    }

    override fun getItemCount() = list.size

    fun addItemList(getList: List<SoughtAfterCategoryResponse>) {
        list.addAll(getList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun checkChange(position: Int) {
        for (idx in list.indices) {
            if (idx == position) {
                list[idx].check = true
                continue
            }
            list[idx].check = false
        }
        //데이터가 변경됐음을 알림
        notifyDataSetChanged()
    }
}