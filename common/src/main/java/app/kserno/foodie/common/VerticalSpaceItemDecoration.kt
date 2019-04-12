package app.kserno.foodie.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by filipsollar on 2019-03-28
 */
class VerticalSpaceItemDecoration(
        private val verticalSpaceHeight: Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                       state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpaceHeight

        }
        outRect.bottom = verticalSpaceHeight

    }
}