package com.giftpunding.osds.ui.login.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.databinding.ItemAnniversaryListBinding
import com.giftpunding.osds.ui.login.AnniversaryListData

class AnniversaryListAdapter(
    val context: Context,
    private val mList: ArrayList<AnniversaryListData>
) :
    RecyclerView.Adapter<AnniversaryListAdapter.LoginEventListViewHolder>() {

    private var oldPosition = 100

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginEventListViewHolder =
        LoginEventListViewHolder(
            ItemAnniversaryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: LoginEventListViewHolder, position: Int) {
        holder.onBind(mList[position])

        holder.itemView.setOnClickListener {
            mList[oldPosition].isChecked = false
            Log.d(
                "AnniListAdapterTest",
                "ClickTest, mList[oldPosition].isChecked: ${mList[oldPosition].isChecked.toString()}"
            )
            mList[position].isChecked = true
            Log.d(
                "AnniListAdapterTest",
                "ClickTest, mList[oldPosition].isChecked: ${mList[oldPosition].isChecked.toString()}"
            )
            holder.onBind(mList[position])
            oldPosition = holder.adapterPosition
        }
    }

    override fun getItemCount(): Int = mList.size

    class LoginEventListViewHolder(private val binding: ItemAnniversaryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: AnniversaryListData) {
            Log.d("AnniListAdapterTest", "adapterPosition: ${adapterPosition.toString()}")
            Log.d("AnniListAdapterTest", "item.isChecked: ${item.isChecked.toString()}")

            binding.btnEventType.apply {
                isChecked = item.isChecked == true // background Color Setting
                text = item.anniversaryType
            }
            if (item.isChecked) binding.btnEventType.setTextColor(Color.WHITE)
        }
    }
}