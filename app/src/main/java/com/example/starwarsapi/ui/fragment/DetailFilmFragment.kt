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
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.presentation.DetailFilmViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import com.example.starwarsapi.ui.adapter.ListStarshipAdapter
import kotlinx.android.synthetic.main.detail_film_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmFragment : BaseFragment() {

    private val viewModel: DetailFilmViewModel by viewModel()
    val args: DetailFilmFragmentArgs by navArgs()

    private val adapterToPeopleList =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action =
                DetailFilmFragmentDirections.actionDetailFilmFragmentToDetailPeopleFragment(people)
            requireView().findNavController().navigate(action)

        }

    private val adapterToStarshipList =
        ListStarshipAdapter(mutableListOf()) {
            val starship = it
            val action =
                DetailFilmFragmentDirections.actionDetailFilmFragmentToDetailStarshipFragment(
                    starship
                )
            requireView().findNavController().navigate(action)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val film = args.Film
        viewModel.getPeopleId(film.people)
        viewModel.getStarshipsId(film.starships)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val film = args.Film
        tvNameInsert.text = film.title
        tvDirectorInsert.text = film.director
        tvReleaseInsert.text = film.releaseDate.toString()
        tvOpeningInsert.text = film.openingCrawl
        peopleRv.adapter = adapterToPeopleList
        starshipRv.adapter = adapterToStarshipList
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_film_fragment, container, false)
    }

    private fun setObserves() {
        viewModel.getList().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemPeople(viewState)
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


    private fun setNewItemPeople(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapterToPeopleList.list.addAll(list) }
        adapterToPeopleList.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?:getString(R.string.erro_desconhecido), Toast.LENGTH_SHORT).show()

    }

}
