package kernycnhyi.vlad.imdbapi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.adapters.MyRecyclerAdapter
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import kernycnhyi.vlad.imdbapi.model.Movie
import kernycnhyi.vlad.imdbapi.presenters.ApiPresenter
import moxy.presenter.InjectPresenter

class ApiDataFragment : BaseFragment(), RecyclerMediaView {

    private val recyclerAdapter: MyRecyclerAdapter by lazy {
        MyRecyclerAdapter()
    }

    @InjectPresenter
    lateinit var presenter: ApiPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_api_data, container, false) as RecyclerView
        return view.apply {
            layoutManager = GridLayoutManager(activity,1)
            adapter = recyclerAdapter
        }
    }

    override fun showMovies(list: List<Movie>) {
        recyclerAdapter.updateList(list)
    }

    override fun showError() {
        showErrorDialog()
    }
}
