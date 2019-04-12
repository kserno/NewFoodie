package com.kserno.foodie.foods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.Food
import com.kserno.foodie.R
import com.kserno.foodie.databinding.ItemFoodBinding

/**
 *  Created by filipsollar on 2019-04-10
 */
class FoodsAdapter : Adapter<Food>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<Food> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<Food>(itemView) {

        val binding = DataBindingUtil.bind<ItemFoodBinding>(itemView)

        override fun update(item: Food) {
            binding?.food = item
            binding?.executePendingBindings()
        }

    }

}