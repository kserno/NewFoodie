package app.kserno.foodie.android.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.android.R
import app.kserno.foodie.android.databinding.ItemFoodBinding
import app.kserno.foodie.common.model.Food

/**
 *  Created by filipsollar on 2019-03-28
 */
class FoodAdapter: Adapter<Food>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<Food> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<Food>(itemView) {

        private val binding = DataBindingUtil.bind<ItemFoodBinding>(itemView)

        override fun update(item: Food) {
            binding?.food = item
            binding?.executePendingBindings()
        }

    }
}