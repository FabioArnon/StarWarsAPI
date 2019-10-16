package com.example.starwarsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.Species
import com.example.starwarsapi.presentation.ShowSpecieActivityViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.android.synthetic.main.item_adapter_list.view.*


class ShowSpecieActivity : AppCompatActivity() {
    private val viewModel: ShowSpecieActivityViewModel by viewModel()
    private val adapter =  ListAdapter(this, mutableListOf<Species>()) { Species, view ->
        view.setOnClickListener {}
        view.nameInsert.text = Species.name
        view.segundoText.text = "classification:"
        view.segundoInsert.text = Species.classification
        view.terceiroText.text = "language:"
        view.terceiroInsert.text = Species.language
        view.quartoText.text = "average lifespan:"
        view.quartoInsert.text = Species.averageLifespan
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_specie)
        setupRecycleView()
        viewModel.getListSpecies()
        setObserves()
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
