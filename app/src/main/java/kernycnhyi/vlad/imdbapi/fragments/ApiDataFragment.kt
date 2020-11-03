package kernycnhyi.vlad.imdbapi.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.adapters.MyRecyclerAdapter
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import kernycnhyi.vlad.imdbapi.model.Movie
import kernycnhyi.vlad.imdbapi.presenters.ApiPresenter
import kotlinx.android.synthetic.main.fragment_api_data.*
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
    ): View? = inflater.inflate(R.layout.fragment_api_data, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = recyclerAdapter
        if(requireContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(),1)
        }
    }

    override fun showMovies(list: List<Movie>) {
        recyclerAdapter.updateList(list)
    }

    override fun showError(error: String?) {
        showErrorDialog(error)
    }
}
