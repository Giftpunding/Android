package com.giftfunding.osds.ui.search.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giftfunding.osds.R
import com.giftfunding.osds.databinding.ItemCategoryImageBinding

class CategoryImageAdapter :
    RecyclerView.Adapter<CategoryImageAdapter.CategoryImageViewHolder>() {

    private val categoryImageItems = mutableListOf<Drawable>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryImageViewHolder = CategoryImageViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category_image,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryImageViewHolder, position: Int) {
        holder.onBind(categoryImageItems[position])
    }

    override fun getItemCount(): Int {
        return this.categoryImageItems.size
    }

    fun setItems(items: List<Drawable>) {
        categoryImageItems.addAll(items)
    }

    class CategoryImageViewHolder(
        private val binding: ItemCategoryImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(image: Drawable) {
            Glide.with(binding.ivCategory.context).load(image).circleCrop().centerInside()
                .into(binding.ivCategory)
        }
    }
}