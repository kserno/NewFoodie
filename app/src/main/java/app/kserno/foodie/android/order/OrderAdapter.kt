package app.kserno.foodie.android.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.kserno.foodie.android.R
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodOrder

/**
 *  Created by filipsollar on 2019-04-12
 */
class OrderAdapter: Adapter<FoodOrder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<FoodOrder> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_order, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<FoodOrder>(itemView) {

        override fun update(item: FoodOrder) {

        }

    }
}