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
import com.example.starwarsapi.presentation.DetailSpecieViewModel
import com.example.starwarsapi.presentation.ViewModelStatusEnum
import com.example.starwarsapi.presentation.ViewState
import com.example.starwarsapi.ui.adapter.ListFilmAdapter
import com.example.starwarsapi.ui.adapter.ListPeopleAdapter
import kotlinx.android.synthetic.main.detail_specie_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailSpecieFragment : BaseFragment() {

    private val viewModel: DetailSpecieViewModel by viewModel()
    val args: DetailSpecieFragmentArgs by navArgs()

    private val adapter =
        ListFilmAdapter(mutableListOf()) {
            val film = it
            val action = DetailSpecieFragmentDirections.actionDetailSpecieFragmentToDetailFilmFragment(film)
            view?.findNavController()?.navigate(action)
        }

    private val adapter2 =
        ListPeopleAdapter(mutableListOf()) {
            val people = it
            val action = DetailSpecieFragmentDirections.actionDetailSpecieFragmentToDetailPeopleFragment(people)
            view?.findNavController()?.navigate(action)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_specie_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val specie = args.Specie
        viewModel.getFilmsId(specie.films)
        viewModel.getPeopleId(specie.people)
        setObserves()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val specie = args.Specie
        tvNameInsert.text = specie.name
        tvClassificationInsert.text = specie.classification
        tvDesignationInsert.text = specie.designation
        tvLifespanInsert.text = specie.averageLifespan
        filmRv.adapter = adapter
        peopleRv.adapter = adapter2

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
        viewState?.data?.let { list -> adapter2.list.addAll(list)}
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
