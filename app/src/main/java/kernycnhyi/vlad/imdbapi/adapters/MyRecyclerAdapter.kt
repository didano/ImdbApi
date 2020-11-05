package kernycnhyi.vlad.imdbapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.labelsHiding
import kernycnhyi.vlad.imdbapi.loadImage
import kernycnhyi.vlad.imdbapi.model.Movie
import kotlinx.android.synthetic.main.recycler_item_card.view.*

class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    var movieList: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_card, parent, false)
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(movieList[position]) {
            labelsHiding(this, holder)
            holder.titleTextView.text = title
            holder.yearTextView.text = year
            holder.posterImageView.loadImage(poster)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val serialBadge = view.serialBadge
        val olderBadge = view.olderMovieBadge
        val posterImageView = view.poster
        val titleTextView = view.titleTextView
        val yearTextView = view.yearTextView
    }

    fun updateList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }
}