package com.example.starwarsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.presentation.ShowPlanetViewModel
import com.example.starwarsapi.models.Planets
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.fragment_show_people.*
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ShowPlanetFragment : BaseFragment() {
    private val viewModel: ShowPlanetViewModel by viewModel()
    private val adapter = ListAdapter( mutableListOf<Planets>()) { planets, view ->
        view.setOnClickListener {}
        view.nameInsert.text = planets.name
        view.segundoText.text = getString(R.string.diameter)
        view.segundoInsert.text = planets.diameter
        view.terceiroText.text = "orbitalPeriod:"
        view.terceiroInsert.text = planets.orbitalPeriod
        view.quartoText.text = getString(R.string.population)
        view.quartoInsert.text = planets.population
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.getListPlanet()
        setObserves()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_planet, container, false)
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
            }
        })
    }

    private fun openNextActivity(viewState: ViewState<List<Planets>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
