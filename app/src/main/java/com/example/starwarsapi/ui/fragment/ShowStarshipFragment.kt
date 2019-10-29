package com.example.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.application.onSearchDelayedOrCanceledListener
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.ShowStarshipViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListStarshipAdapter
import kotlinx.android.synthetic.main.fragment_show_people.rvSearchActivityList
import kotlinx.android.synthetic.main.fragment_show_starship.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowStarshipFragment : BaseFragment() {

    private val viewModel: ShowStarshipViewModel by viewModel()

    private val adapter =
        ListStarshipAdapter(mutableListOf()) {
            val starship = it
            val action =
                ShowStarshipFragmentDirections.actionShowStarshipFragmentToDetailStarshipFragment(starship)
            view?.findNavController()?.navigate(action)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getListStarship()
        setObserves()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        svStarship.onSearchDelayedOrCanceledListener {
            it?.let { it1 ->
                adapter.list.clear()
                viewModel.getListStarship(it1)
            }

            if (it == "") {
                adapter.list.clear()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_starship, container, false)
    }

    private fun setupRecycleView() {
        rvSearchActivityList.onScrollListener {
            if (LOADING != viewModel.getList().value?.status && !rvSearchActivityList.canScrollVertically(
                    1
                )
                && ERROR != viewModel.getList().value?.status && ERROR_LIST_EMPTY != viewModel.getList().value?.status
            ) {
                viewModel.nextPage()
            }
        }
        rvSearchActivityList.adapter = adapter
    }

    private fun setObserves() {
        viewModel.getList().observe(this, Observer { viewState ->
            when (viewState.status) {
                SUCCESS -> openNextActivity(viewState)
                ERROR -> onError(viewState.error)
                LOADING -> showLoading()
            }
        })
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
    }

    private fun openNextActivity(viewState: ViewState<List<Starships>, ViewModelStatusEnum>?) {
        progressBar.visibility = View.GONE
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
