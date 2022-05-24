package com.giftpunding.osds.ui.search.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.giftpunding.osds.R

class CategoryImageAdapter:
    RecyclerView.Adapter<CategoryImageAdapter.CategoryImageViewHolder>() {

    private var mCategoryImageItemList: ArrayList<Drawable?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_image, parent, false)
        return CategoryImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryImageViewHolder, position: Int) {
        mCategoryImageItemList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return this.mCategoryImageItemList.size
    }

    fun addItems(itemList: ArrayList<Drawable?>){
        this.mCategoryImageItemList.addAll(itemList)
    }

    class CategoryImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var categoryImage: ImageView
        fun bind(image: Drawable) {
            categoryImage = itemView.findViewById(R.id.iv_category)
            categoryImage.setImageDrawable(image)
        }
    }
}