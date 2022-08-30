package com.giftpunding.osds.ui.anniversary.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.databinding.ItemAnniversaryListBinding
import com.giftpunding.osds.ui.anniversary.AnniversaryListData

class AnniversaryListAdapter(
    val context: Context,
    private val mList: ArrayList<AnniversaryListData>
) :
    RecyclerView.Adapter<AnniversaryListAdapter.LoginEventListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginEventListViewHolder =
        LoginEventListViewHolder(
            ItemAnniversaryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: LoginEventListViewHolder, position: Int) {
        holder.onBind(mList[position])

        holder.itemView.setOnClickListener {
            mList[position].isChecked = true
            holder.onBind(mList[position])
        }
    }

    override fun getItemCount(): Int = mList.size

    class LoginEventListViewHolder(private val binding: ItemAnniversaryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: AnniversaryListData) {
            binding.btnEventType.apply {
                isChecked = item.isChecked == true // background Color Setting
                text = item.anniversaryType
            }
            if (item.isChecked) binding.btnEventType.setTextColor(Color.WHITE)
        }
    }
}