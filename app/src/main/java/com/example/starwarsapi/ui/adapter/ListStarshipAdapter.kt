package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.ui.holder.ViewStarshipHolder

class ListStarshipAdapter(
    val list: MutableList<Starships>,
    private val onClick: (Starships) -> Unit
): RecyclerView.Adapter<ViewStarshipHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewStarshipHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewStarshipHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewStarshipHolder, position: Int) = holder.bindView(list[position])
}