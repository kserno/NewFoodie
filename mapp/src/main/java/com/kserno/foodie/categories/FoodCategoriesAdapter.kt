package com.kserno.foodie.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.FoodCategory
import com.kserno.foodie.R
import com.kserno.foodie.databinding.ItemFoodCategoryBinding

/**
 *  Created by filipsollar on 2019-03-27
 */
class FoodCategoriesAdapter: Adapter<FoodCategory>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_category, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<FoodCategory>(itemView) {

        private val binding = DataBindingUtil.bind<ItemFoodCategoryBinding>(itemView)

        override fun update(item: FoodCategory) {
            binding?.foodCategory = item
            binding?.notifyChange()
        }

    }
}