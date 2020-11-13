package kernycnhyi.vlad.imdbapi.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import kernycnhyi.vlad.imdbapi.MainActivity
import kernycnhyi.vlad.imdbapi.R
import kernycnhyi.vlad.imdbapi.adapters.MyRecyclerAdapter
import kernycnhyi.vlad.imdbapi.interfaces.RecyclerMediaView
import kernycnhyi.vlad.imdbapi.model.BaseMovieModel
import kernycnhyi.vlad.imdbapi.presenters.ApiPresenter
import kotlinx.android.synthetic.main.fragment_api_data.*
import moxy.presenter.InjectPresenter

class ApiDataFragment : BaseFragment(), RecyclerMediaView {

    private val recyclerAdapter: MyRecyclerAdapter by lazy {
        MyRecyclerAdapter()
    }

    @InjectPresenter
    lateinit var presenter: ApiPresenter
    private var query = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_api_data, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.columns_count))
        floatingActionButton.setOnClickListener {
            presenter.doQuery(query)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_actionbar, menu)
        val actionMenuButton = menu.findItem(R.id.actionSearch)
        val searchView = SearchView((activity as MainActivity).supportActionBar?.themedContext)
        actionMenuButton.setActionView(searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    presenter.doQuery(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun showMovies(list: List<BaseMovieModel>) {
        recyclerAdapter.updateList(list)
    }

    override fun showError(error: String?) {
        showErrorDialog(error)
    }

}
