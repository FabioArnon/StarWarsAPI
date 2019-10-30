package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.film.Films
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewFilmHolder(
    private val view: View,
    private val onClick: (Films) -> Unit
): RecyclerView.ViewHolder(view){
    fun bindView(item: Films){
        view.setOnClickListener { onClick(item) }
        view.name.text = view.context.getString(R.string.title)
        view.nameInsert.text = item.title
        view.segundoText.text = view.context.getString(R.string.episode_id)
        view.segundoInsert.text = item.episodeId.toString()
        view.terceiroText.text = view.context.getString(R.string.release_date)
        view.terceiroInsert.text = item.releaseDate.toString()
        view.quartoText.text = view.context.getString(R.string.director)
        view.quartoInsert.text = item.director
    }
}