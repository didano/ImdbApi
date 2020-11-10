package kernycnhyi.vlad.imdbapi

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun View.isVisible(condition: Boolean) {
    visibility = if (condition)
        View.VISIBLE
    else
        View.GONE
}