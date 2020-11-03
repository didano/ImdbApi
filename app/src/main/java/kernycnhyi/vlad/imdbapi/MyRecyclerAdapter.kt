package kernycnhyi.vlad.imdbapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kernycnhyi.vlad.imdbapi.model.Movie
import kotlinx.android.synthetic.main.recycler_item_card.view.*

class MyRecyclerAdapter(private var movieList: List<Movie>):RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_card,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = movieList[position].title
        holder.yearTextView.text = movieList[position].year
        Glide.with(holder.itemView).load(movieList[position].poster).into(holder.posterImageView)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val posterImageView  = view.poster
        val titleTextView = view.titleTextView
        val yearTextView = view.yearTextView
    }

    fun updateList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }
}