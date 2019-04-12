package app.kserno.foodie.android

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *  Created by filipsollar on 2019-03-27
 */
object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView)
                .load(imageUrl)
                .into(imageView)
    }

}