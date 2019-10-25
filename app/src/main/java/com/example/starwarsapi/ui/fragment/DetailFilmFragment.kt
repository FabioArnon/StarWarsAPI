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
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.DetailFilmViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import com.example.starwarsapi.ui.adapter.ListStarshipAdapter
import kotlinx.android.synthetic.main.detail_film_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmFragment : BaseFragment() {

    private val viewModel: DetailFilmViewModel by viewModel()
    val args: DetailFilmFragmentArgs by navArgs()

    private val adapter =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action =
                DetailFilmFragmentDirections.actionDetailFilmFragmentToDetailPeopleFragment(people)
            view?.findNavController()?.navigate(action)
        }

    private val adapter2 =
        ListStarshipAdapter(mutableListOf()) {
            val starship = it
            val action =
                DetailFilmFragmentDirections.actionDetailFilmFragmentToDetailStarshipFragment(
                    starship
                )
            view?.findNavController()?.navigate(action)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val film = args.Film
        viewModel.nextPeople(film)
        viewModel.nextStarship(film)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val film = args.Film
        tvNameInsert.text = film.title
        tvDirectorInsert.text = film.director
        tvReleaseInsert.text = film.releaseDate.toString()
        tvOpeningInsert.text = film.openingCrawl
        peopleRv.adapter = adapter
        starshipRv.adapter = adapter2
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
                    Toast.makeText(context, "Erro desconhecido", Toast.LENGTH_SHORT).show()

            }
        })

        viewModel.getListStarships().observe(this, Observer { viewState ->
            when (viewState.status) {
                ViewModelStatusEnum.SUCCESS -> setNewItemStarship(viewState)
                ViewModelStatusEnum.ERROR -> onError(viewState.error)
                ViewModelStatusEnum.ERROR_LIST_EMPTY ->
                    Toast.makeText(context, "Erro desconhecido", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun setNewItemStarship(viewState: ViewState<List<Starships>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter2.list.addAll(list) }
        adapter2.notifyDataSetChanged()
    }


    private fun setNewItemPeople(viewState: ViewState<List<People>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()

    }

}
