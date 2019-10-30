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
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.presentation.DetailPeopleViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListStarshipAdapter
import kotlinx.android.synthetic.main.detail_people_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPeopleFragment : BaseFragment() {

    private val viewModel: DetailPeopleViewModel by viewModel()
    val args: DetailPeopleFragmentArgs by navArgs()

    private val adapterToFilmList =
        ListFilmAdapter(mutableListOf()) {
            val film = it
            val action =
                DetailPeopleFragmentDirections.actionDetailPeopleFragmentToDetailFilmFragment(film)
            requireView().findNavController().navigate(action)
        }

    private val adapterToStarshipList =
        ListStarshipAdapter(mutableListOf()) {
            val starship = it
            val action =
                DetailPeopleFragmentDirections.actionDetailPeopleFragmentToDetailStarshipFragment(
                    starship
                )
            requireView().findNavController().navigate(action)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val people = args.People
        viewModel.getFilmsId(people.films)
        viewModel.getStarshipsId(people.starships)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val people = args.People
        tvNameInsert.text = people.name
        tvBirthInsert.text = people.birthYear
        tvGenderInsert.text = people.gender
        tvSkinInsert.text = people.skinColor
        filmRv.adapter = adapterToFilmList
        starshipRv.adapter = adapterToStarshipList
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_people_fragment, container, false)
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

        viewModel.getListStarships().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemStarship(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context,getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        })
    }


    private fun setNewItemStarship(viewState: ViewState<List<Starships>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapterToStarshipList.list.addAll(list) }
        adapterToStarshipList.notifyDataSetChanged()
    }


    private fun setNewItemFilm(viewState: ViewState<List<Films>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapterToFilmList.list.addAll(list) }
        adapterToFilmList.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?:getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()
    }

}
