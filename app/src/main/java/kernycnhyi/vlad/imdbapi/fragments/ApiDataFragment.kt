package kernycnhyi.vlad.imdbapi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kernycnhyi.vlad.imdbapi.MyRecyclerAdapter
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import kernycnhyi.vlad.imdbapi.model.Movie
import kernycnhyi.vlad.imdbapi.presenters.ApiPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ApiDataFragment : MvpAppCompatFragment(), RecyclerMediaView {

    lateinit var recyclerAdapter: MyRecyclerAdapter

    @InjectPresenter
    lateinit var presenter: ApiPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerAdapter = MyRecyclerAdapter(emptyList())
        val view= inflater.inflate(R.layout.fragment_api_data, container, false) as RecyclerView
        view.apply {
            layoutManager = GridLayoutManager(activity,1)
            adapter = recyclerAdapter
        }
        return view
    }

    override fun showMovies(list: List<Movie>) {
        recyclerAdapter.updateList(list)
    }

    override fun showError() {

    }
}
