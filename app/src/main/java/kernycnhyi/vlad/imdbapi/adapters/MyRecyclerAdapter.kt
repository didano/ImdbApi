package kernycnhyi.vlad.imdbapi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.isVisible
import kernycnhyi.vlad.imdbapi.loadImage
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel
import kernycnhyi.vlad.imdbapi.model.Series
import kotlinx.android.synthetic.main.recycler_item_card.view.*
import kotlinx.android.synthetic.main.recycler_series_item_card.view.*

class MyRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movieList: List<BaseMovieModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SERIES_HOLDER) {
            SerialHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_series_item_card, parent, false)
            )
        } else {
            MovieHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_card, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun getItemViewType(position: Int): Int {
        return if (movieList[position] is Series) {
            SERIES_HOLDER
        } else {
            MOVIES_HOLDER
        }
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val olderBadge = view.olderMovieBadge
        val posterImageView = view.poster
        val titleTextView = view.titleTextView
        val yearTextView = view.yearTextView

        fun bindHolder() {
            with(movieList[adapterPosition]) {
                Log.d("VISIBILITY", isYounger.toString())
                olderBadge.isVisible(isYounger)
                posterImageView.loadImage(poster)
                titleTextView.text = title
                yearTextView.text = year
            }
        }
    }

    inner class SerialHolder(view: View) : RecyclerView.ViewHolder(view) {
        var olderSeriesBadge = view.olderSeriesBadge
        var seriesPoster = view.seriesPoster
        var seriesTitleTextView = view.seriesTitleTextView
        var seriesYearTextView = view.seriesYearTextView

        fun bindHolder() {
            with(movieList[adapterPosition]) {
                olderSeriesBadge.isVisible(isYounger)
                seriesPoster.loadImage(poster)
                seriesTitleTextView.text = title
                seriesYearTextView.text = year
            }
        }
    }

    fun updateList(list: List<BaseMovieModel>) {
        movieList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == SERIES_HOLDER) {
            (holder as SerialHolder).bindHolder()
        } else {
            (holder as MovieHolder).bindHolder()
        }
    }

    companion object {
        const val SERIES_HOLDER = 1
        const val MOVIES_HOLDER = 2
    }
}