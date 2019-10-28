package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.models.Films
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewFilmHolder(
    private val view: View,
    private val onClick: (Films) -> Unit
): RecyclerView.ViewHolder(view){
    fun bindView(item: Films){
        view.setOnClickListener { onClick(item) }
        view.name.text = "title: "
        view.nameInsert.text = item.title
        view.segundoText.text = "episode_id: "
        view.segundoInsert.text = item.episodeId.toString()
        view.terceiroText.text = "release date: "
        view.terceiroInsert.text = item.releaseDate.toString()
        view.quartoText.text = "director: "
        view.quartoInsert.text = item.director
    }
}