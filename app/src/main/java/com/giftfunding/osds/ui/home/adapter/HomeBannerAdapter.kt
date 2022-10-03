package com.giftfunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.giftfunding.osds.databinding.ContentBannerBinding

//배너 이미지는 drawable을 임시로 사용
class HomeBannerAdapter(val context: Context, private val bannerList: List<Int>) :
    RecyclerView.Adapter<HomeBannerAdapter.HomeBannerHolder>() {

    inner class HomeBannerHolder(val binding: ContentBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Int) {
            binding.ivBanner.setImageResource(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerHolder {
        val view = ContentBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerHolder, position: Int) {
        holder.bind(bannerList[position % bannerList.size])
    }

    override fun getItemCount() = Int.MAX_VALUE
}