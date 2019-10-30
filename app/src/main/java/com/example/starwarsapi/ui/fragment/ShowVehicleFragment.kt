package com.example.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.starwarsapi.R
import com.example.starwarsapi.application.xt.ERRO_DESCONHECIDO
import com.example.starwarsapi.application.xt.onScrollListener
import com.example.starwarsapi.application.xt.onSearchDelayedOrCanceledListener
import com.example.starwarsapi.models.vehicle.Vehicles
import com.example.starwarsapi.presentation.ShowVehicleViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum.*
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.ui.adapter.ListVehicleAdapter
import kotlinx.android.synthetic.main.fragment_show_people.rvSearchActivityList
import kotlinx.android.synthetic.main.fragment_show_vehicle.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowVehicleFragment : BaseFragment() {
    private val viewModel: ShowVehicleViewModel by viewModel()
    private val adapter =
        ListVehicleAdapter(mutableListOf()) {
            val vehicles = it
            val action =
                ShowVehicleFragmentDirections.actionShowVehicleFragmentToDetailVehicleFragment(
                    vehicles
                )
            requireView().findNavController().navigate(action)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getListVehicle()
        setObserves()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        svVehicle.onSearchDelayedOrCanceledListener {
            it?.let { search ->
                adapter.list.clear()
                viewModel.getListVehicle(search)
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
        return inflater.inflate(R.layout.fragment_show_vehicle, container, false)
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
                else -> Unit
            }
        })
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?:getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
    }

    private fun openNextActivity(viewState: ViewState<List<Vehicles>, ViewModelStatusEnum>?) {
        progressBar.visibility = View.GONE
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }
}
