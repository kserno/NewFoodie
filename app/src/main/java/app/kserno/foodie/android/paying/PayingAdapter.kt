package app.kserno.foodie.android.paying

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.kserno.foodie.android.R
import app.kserno.foodie.android.databinding.ItemPayingOrderBinding
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.FoodOrder

/**
 *  Created by filipsollar on 2019-04-12
 */
class PayingAdapter: RecyclerView.Adapter<PayingAdapter.ViewHolder>() {

    var items : List<FoodOrder> = mutableListOf()
    set(value) {
        field = value
        selection = MutableList(items.size) { false }
        notifyDataSetChanged()
    }
    private var selection = mutableListOf<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paying_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(items[position], selection[position])
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemPayingOrderBinding>(itemView)

        fun update(item: FoodOrder, selected: Boolean) {
            binding?.food = item.food
            if (selected) {
                itemView.setBackgroundColor(Color.parseColor("#d2d2d2"))
            } else {
                itemView.setBackgroundColor(Color.parseColor("#ffffff"))
            }
            binding?.executePendingBindings()
            itemView.setOnClickListener {
                selection[adapterPosition] = !selection[adapterPosition]
                notifyItemChanged(adapterPosition)
            }
        }

    }

    fun getSelected() : List<FoodOrder>{
        val result = ArrayList<FoodOrder>()
        selection.forEachIndexed { index, b ->
            if (b) {
                result.add(items[index])
            }
        }
        return result
    }

}