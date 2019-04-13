package com.kserno.foodie.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.FoodOrder
import com.kserno.foodie.R
import com.kserno.foodie.databinding.ItemOrderBinding

/**
 *  Created by filipsollar on 2019-04-13
 */
class TableAdapter: Adapter<FoodOrder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<FoodOrder> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<FoodOrder>(itemView) {

        val binding = DataBindingUtil.bind<ItemOrderBinding>(itemView)

        override fun update(item: FoodOrder) {
            binding?.food = item.food
            binding?.foodOrder = item
            binding?.executePendingBindings()
        }


    }

}
