package com.example.starwarsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.presentation.ShowFilmViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.fragment_show_people.*
import kotlinx.android.synthetic.main.item_adapter_list.view.*


class ShowFilmFragment: BaseFragment() {
    private val viewModel: ShowFilmViewModel by viewModel()
    private val adapter =  ListAdapter(mutableListOf<Films>()) { film, view ->
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.getListFilms()
        setObserves()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_film, container, false)
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
