package matshagen.example.androidjetpacktest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import matshagen.example.androidjetpacktest.R
import matshagen.example.androidjetpacktest.viewModel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val placeListAdapter = PlaceListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        placeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = placeListAdapter
        }

        refreshLayout.setOnRefreshListener {
            placeList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.places.observe(viewLifecycleOwner, Observer { places ->
            places.let {
                placeList.visibility = View.VISIBLE
                placeListAdapter.updatePlaceList(places)
            }
        })
        viewModel.placesLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    placeList.visibility = View.GONE
                }
            }
        })
    }

}
