package com.giftpunding.osds.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.data.response.home.HomeMerchandiseResponse
import com.giftpunding.osds.databinding.ItemHomeMerchandiseListBinding


class HomeMerchandiseAdapter(
    private val context: Context,
    private val getMerchandiseList: List<HomeMerchandiseResponse>
) : RecyclerView.Adapter<HomeMerchandiseAdapter.HomeMerchandiseHolder>() {
    inner class HomeMerchandiseHolder(mainBinding: ItemHomeMerchandiseListBinding) :
        RecyclerView.ViewHolder(mainBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMerchandiseHolder {
        val view = ItemHomeMerchandiseListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeMerchandiseHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMerchandiseHolder, position: Int) {
        with(holder){
            with(getMerchandiseList[position]){
                //아이템 클릭
            }
        }
    }

    override fun getItemCount() = getMerchandiseList.size
}
