package kernycnhyi.vlad.imdbapi

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kernycnhyi.vlad.imdbapi.adapters.MyRecyclerAdapter
import kernycnhyi.vlad.imdbapi.model.Movie

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun labelsHiding(movie: Movie, holder: MyRecyclerAdapter.MyViewHolder) {
    if (movie.isYounger)
        holder.olderBadge.visibility = View.VISIBLE
    else
        holder.olderBadge.visibility = View.GONE

    if (movie.type == "series") {
        holder.serialBadge.visibility = View.VISIBLE
    } else {
        holder.serialBadge.visibility = View.GONE
    }
}