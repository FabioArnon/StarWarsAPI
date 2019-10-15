package com.example.starwarsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.activity_show.*
import androidx.lifecycle.Observer
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.ShowStarshipActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ShowStarshipActivity : AppCompatActivity() {
    private val viewModel: ShowStarshipActivityViewModel by viewModel()
    private val adapter = ListAdapter(this, mutableListOf<Starships>()) { starships, view ->
        view.setOnClickListener {}
        view.nameInsert.text = starships.name
        view.segundoText.text = "model"
        view.segundoInsert.text = starships.model
        view.terceiroText.text = "Cost in Credits:"
        view.terceiroInsert.text = starships.cost
        view.quartoText.text = "passengers"
        view.quartoInsert.text = starships.passengers
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_planet)
        setupRecycleView()
        viewModel.getListStarship()
        setObserves()
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

    private fun openNextActivity(viewState: ViewState<List<Starships>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
