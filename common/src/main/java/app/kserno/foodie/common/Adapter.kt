package app.kserno.foodie.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by filipsollar on 2019-03-27
 */
abstract class Adapter<ITEM>: RecyclerView.Adapter<Adapter.ViewHolder<ITEM>>() {

    var items: List<ITEM> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var listener: Listener<ITEM>? = null

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder<ITEM>, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.onItemSelected(items[position])
        }
        holder.update(items[position])
    }


    abstract class ViewHolder<ITEM>(itemView: View): RecyclerView.ViewHolder(itemView){


        abstract fun update(item: ITEM)
    }

    interface Listener<ITEM> {
        fun onItemSelected(item: ITEM)
    }


}