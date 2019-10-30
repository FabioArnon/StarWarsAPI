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
import com.example.starwarsapi.presentation.DetailStarshipViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import kotlinx.android.synthetic.main.detail_starship_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailStarshipFragment : BaseFragment() {

    private val viewModel: DetailStarshipViewModel by viewModel()
    val args: DetailStarshipFragmentArgs by navArgs()

    private val adapterToFilmList =
        ListFilmAdapter(mutableListOf()) {
            val film = it
            val action = DetailStarshipFragmentDirections.actionDetailStarshipFragmentToDetailFilmFragment(film)
            requireView().findNavController().navigate(action)
        }

    private val adapterToPeopleList =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action = DetailStarshipFragmentDirections.actionDetailStarshipFragmentToDetailPeopleFragment(people)
            requireView().findNavController().navigate(action)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_starship_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val starship = args.Starship
        viewModel.getFilmsId(starship.films)
        viewModel.getPeopleId(starship.pilots)
        setObserves()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val starship = args.Starship
        tvNameInsert.text = starship.name
        tvModelInsert.text = starship.model
        tvManufacturerInsert.text = starship.manufacturer
        tvClassInsert.text = starship.starshipClass
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

    private fun setNewItemPilot(viewState: ViewState<List<People>, ViewModelStatusEnum>) {
        viewState.data?.let { list -> adapterToPeopleList.list.addAll(list)}
        adapterToPeopleList.notifyDataSetChanged()
    }

    private fun setNewItemFilm(viewState: ViewState<List<Films>, ViewModelStatusEnum>) {
        viewState.data?.let { list -> adapterToFilmList.list.addAll(list) }
        adapterToFilmList.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?:getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()

    }

}
