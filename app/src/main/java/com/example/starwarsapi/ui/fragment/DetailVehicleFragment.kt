package com.example.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.starwarsapi.R
import com.example.starwarsapi.application.xt.ERRO_DESCONHECIDO
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.presentation.DetailVehicleViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import kotlinx.android.synthetic.main.detail_vehicle_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailVehicleFragment : BaseFragment() {

    private val viewModel: DetailVehicleViewModel by viewModel()
    val args: DetailVehicleFragmentArgs by navArgs()

    private val adapterToFilmList =
        ListFilmAdapter(mutableListOf()) {
            val film = it
            val action =
                DetailVehicleFragmentDirections.actionDetailVehicleFragmentToDetailFilmFragment(film)
            requireView().findNavController().navigate(action)
        }

    private val adapterToPeopleList =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action =
                DetailVehicleFragmentDirections.actionDetailVehicleFragmentToDetailPeopleFragment(
                    people
                )
            requireView().findNavController().navigate(action)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_vehicle_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vehicle = args.Vehicle
        viewModel.getFilmsId(vehicle.films)
        viewModel.getPeopleId(vehicle.pilots)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vehicle = args.Vehicle
        tvNameInsert.text = vehicle.name
        tvModelInsert.text = vehicle.model
        tvManufacturerInsert.text = vehicle.manufacturer
        tvClassInsert.text = vehicle.vehicleClass
        filmRv.adapter = adapterToFilmList
        pilotRv.adapter = adapterToPeopleList
    }

    private fun setObserves() {
        viewModel.getList().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemFilm(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context,getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()

                else -> Unit
            }
        })
        viewModel.getListPeople().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemPilot(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context,getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        })
    }

    private fun setNewItemPilot(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapterToPeopleList.list.addAll(list) }
        adapterToPeopleList.notifyDataSetChanged()
    }

    private fun setNewItemFilm(viewState: ViewState<List<Films>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapterToFilmList.list.addAll(list) }
        adapterToFilmList.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?:getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()

    }

}