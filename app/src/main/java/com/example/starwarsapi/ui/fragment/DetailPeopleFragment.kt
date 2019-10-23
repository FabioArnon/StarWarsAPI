package com.example.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.starwarsapi.R
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.DetailPeopleViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListStarshipAdapter
import kotlinx.android.synthetic.main.detail_people_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPeopleFragment : BaseFragment() {

    private val viewModel: DetailPeopleViewModel by viewModel()
    val args: DetailPeopleFragmentArgs by navArgs()

    private val adapter =
        ListFilmAdapter(mutableListOf()) {

        }

    private val adapter2 =
        ListStarshipAdapter(mutableListOf()) {

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val people = args.People
        tvNameInsert.text = people.name
        tvBirthInsert.text = people.birthYear
        tvGenderInsert.text = people.gender
        tvSkinInsert.text = people.skinColor
        filmRv.adapter = adapter
        starshipRv.adapter = adapter2
        setObserves()
        viewModel.nextFilm(people)
        viewModel.nextStarship(people)
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


    private fun setNewItemFilm(viewState: ViewState<List<Films>, ViewModelStatusEnum>?) {
        viewState?.data?.let { list -> adapter.list.addAll(list) }
        adapter.notifyDataSetChanged()
    }

    private fun onError(error: Throwable?) {
        Toast.makeText(context, error?.message ?: "Erro desconhecido", Toast.LENGTH_SHORT).show()

    }

}
