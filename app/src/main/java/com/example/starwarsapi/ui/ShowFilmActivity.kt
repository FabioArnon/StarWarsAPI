package com.example.starwarsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.presentation.ShowFilmActivityViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.android.synthetic.main.item_adapter_list.view.*


class ShowFilmActivity : AppCompatActivity() {
    private val viewModel: ShowFilmActivityViewModel by viewModel()
    private val adapter =  ListAdapter(this, mutableListOf<Films>()) { film, view ->
        view.setOnClickListener {}
        view.name.text = "title:"
        view.nameInsert.text = film.title
        view.segundoText.text = "episode_id:"
        view.segundoInsert.text = film.episodeId.toString()
        view.terceiroText.text = "release date:"
        view.terceiroInsert.text = film.releaseDate.toString()
        view.quartoText.text = "director:"
        view.quartoInsert.text = film.director
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_film)
        setupRecycleView()
        viewModel.getListFilms()
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

    private fun openNextActivity(viewState: ViewState<List<Films>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
