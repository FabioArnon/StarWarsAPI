package com.example.starwarsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.People
import com.example.starwarsapi.presentation.ShowPeopleViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import kotlinx.android.synthetic.main.fragment_show_people.*
import kotlinx.android.synthetic.main.item_adapter_list.view.*


class ShowPeopleFragment : BaseFragment() {
    private val viewModel: ShowPeopleViewModel by viewModel()
    private val adapter =  ListAdapter( mutableListOf<People>()) { people, view ->
        view.setOnClickListener {}
        view.nameInsert.text = people.name
        view.segundoInsert.text = people.gender
        view.terceiroInsert.text = people.height
        view.quartoInsert.text = people.mass
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel.getListPeople()
        setObserves()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_people, container, false)
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

    private fun openNextActivity(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
            viewState?.data?.let { list -> adapter.list.addAll(list) }
            adapter.notifyDataSetChanged()
    }
}
