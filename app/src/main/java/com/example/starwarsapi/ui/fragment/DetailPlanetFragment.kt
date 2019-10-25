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
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.People
import com.example.starwarsapi.presentation.DetailPlanetViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import kotlinx.android.synthetic.main.detail_planet_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPlanetFragment : BaseFragment() {

    private val viewModel: DetailPlanetViewModel by viewModel()
    val args: DetailPlanetFragmentArgs by navArgs()

    private val adapter =
        ListFilmAdapter(mutableListOf()) {
            val film = it
            val action =
                DetailPlanetFragmentDirections.actionDetailPlanetFragmentToDetailFilmFragment(film)
            view?.findNavController()?.navigate(action)
        }

    private val adapter2 =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action =
                DetailPlanetFragmentDirections.actionDetailPlanetFragmentToDetailPeopleFragment(people)
            view?.findNavController()?.navigate(action)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_planet_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val planet = args.Planet
        viewModel.nextFilm(planet)
        viewModel.nextResident(planet)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val planet = args.Planet
        tvNameInsert.text = planet.name
        tvPopulationInsert.text = planet.population
        tvTerrainInsert.text = planet.terrain
        tvOrbitalInsert.text = planet.orbitalPeriod
        filmRv.adapter = adapter
        residentsRv.adapter = adapter2

    }

    private fun setObserves() {
        viewModel.getList().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemFilm(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context, "Erro desconhecido", Toast.LENGTH_SHORT).show()

            }
        })
        viewModel.getListPeople().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemResident(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context, "Erro desconhecido", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setNewItemResident(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter2.list.addAll(list) }
        adapter2.notifyDataSetChanged()
    }

    private fun setNewItemFilm(viewState: ViewState<List<Films>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()

    }

}