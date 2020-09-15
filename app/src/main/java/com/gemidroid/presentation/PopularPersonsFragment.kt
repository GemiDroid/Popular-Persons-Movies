package com.gemidroid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gemidroid.data.model.PopularPerson
import com.gemidroid.moviesdb.R
import com.gemidroid.util.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_popular_persons.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class PopularPersonsFragment : Fragment() {

    private val popularPersonsViewModel by viewModel<PopularPersonsViewModel>()
    private lateinit var popularPersonsAdapter: PopularPersonsAdapter
    var popularPersons = mutableListOf<PopularPerson>()
    var pageNumber: Int = 2

    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_persons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        popularPersonsViewModel.popularPersons.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty())
                Snackbar.make(rec_persons, getString(R.string.no_data_found), Snackbar.LENGTH_SHORT)
                    .show()
            else {
                if (!popularPersons.containsAll(it))
                    popularPersons.addAll(it)
                popularPersonsAdapter.notifyItemInserted(popularPersons.size - 1)
            }
        })

        popularPersonsViewModel.progress.observe(viewLifecycleOwner, {
            if (it)
                progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

        popularPersonsViewModel.error.observe(viewLifecycleOwner, Observer {
            val error = when (it) {
                is UnknownHostException -> getString(R.string.no_internet_connection)
                else -> getString(R.string.general_issue)
            }
            Snackbar.make(rec_persons, error, Snackbar.LENGTH_SHORT).show()
        })

        val linearLayoutManager = LinearLayoutManager(requireContext())
        rec_persons.layoutManager = linearLayoutManager
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadMore(pageNumber = pageNumber++)
            }
        }
        rec_persons.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
    }

    private fun initAdapter() {
        popularPersonsAdapter = PopularPersonsAdapter(popularPersons) { personId ->
            val bundle = bundleOf(Constants.PERSON_ID to personId)
            findNavController().navigate(
                R.id.action_popularPersonsFragment_to_personDetailsFragment,
                bundle
            )
        }
        rec_persons.adapter = popularPersonsAdapter
    }


    private fun loadMore(pageNumber: Int) {
        popularPersonsViewModel.loadMore(pageNumber)
    }
}