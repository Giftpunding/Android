package com.giftpunding.osds.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ItemHomeMerchandiseFooterBinding
import com.giftpunding.osds.databinding.ItemHomeMerchandiseListBinding

const val FOOTER = 0
const val ITEM = 1

class HomeMerchandiseAdapter(
    private val context: Context,
    private val getMerchandiseList: List<HomeMerchandiseResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class HomeMerchandiseHolder(mainBinding: ItemHomeMerchandiseListBinding) :
        RecyclerView.ViewHolder(mainBinding.root)

    inner class HomeMerchandiseFooterHolder(footerHolder: ItemHomeMerchandiseFooterBinding) :
        RecyclerView.ViewHolder(footerHolder.root)

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            getMerchandiseList.size -> FOOTER
            else -> ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            FOOTER -> {
                val view = ItemHomeMerchandiseFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeMerchandiseFooterHolder(view)
            }
            else -> {
                val view = ItemHomeMerchandiseListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeMerchandiseHolder(view)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeMerchandiseFooterHolder) {
            //클릭 하면 전체 목록
        } else {
            with(holder as HomeMerchandiseHolder) {
                //리스트에 데이터 연결
            }
        }
    }

    override fun getItemCount() = getMerchandiseList.size + 1
}
