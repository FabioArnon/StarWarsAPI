package com.example.starwarsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.Species
import com.example.starwarsapi.presentation.ShowSpecieViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.fragment_show_people.*
import kotlinx.android.synthetic.main.item_adapter_list.view.*


class ShowSpecieFragment : BaseFragment() {
    private val viewModel: ShowSpecieViewModel by viewModel()
    private val adapter =  ListAdapter( mutableListOf<Species>()) { Species, view ->
        view.setOnClickListener {}
        view.nameInsert.text = Species.name
        view.segundoText.text = "classification:"
        view.segundoInsert.text = Species.classification
        view.terceiroText.text = "language:"
        view.terceiroInsert.text = Species.language
        view.quartoText.text = "average lifespan:"
        view.quartoInsert.text = Species.averageLifespan
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.getListSpecies()
        setObserves()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_specie, container, false)
    }


    private fun setupRecycleView() {
        rvSearchActivityList.onScrollListener {
            if(LOADING != viewModel.getList().value?.status && !rvSearchActivityList.canScrollVertically(1)
                && ERROR != viewModel.getList().value?.status && ERROR_LIST_EMPTY != viewModel.getList().value?.status) {
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

    private fun openNextActivity(viewState: ViewState<List<Species>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
