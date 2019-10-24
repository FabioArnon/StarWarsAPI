package com.example.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.starwarsapi.R
import com.example.starwarsapi.application.onScrollListener
import com.example.starwarsapi.models.People
import com.example.starwarsapi.presentation.ShowPeopleViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import kotlinx.android.synthetic.main.fragment_show_people.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShowPeopleFragment : BaseFragment() {


    private val viewModel: ShowPeopleViewModel by viewModel()

    private val adapter =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action = ShowPeopleFragmentDirections.actionShowPeopleFragmentToDetailPeopleFragment(people)
            view?.findNavController()?.navigate(action)
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

    private fun openNextActivity(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
