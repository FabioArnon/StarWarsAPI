package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.ui.holder.ViewFilmHolder

class ListFilmAdapter(
    val list: MutableList<Films>,
    private val onClick: (Films) -> Unit
) :
    RecyclerView.Adapter<ViewFilmHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewFilmHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewFilmHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewFilmHolder, position: Int) =
        holder.bindView(list[position])
}