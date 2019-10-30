package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.planet.Planets
import com.example.starwarsapi.ui.holder.ViewPlanetHolder

class ListPlanetAdapter(
    val list: MutableList<Planets>,
    private val onClick: (Planets) -> Unit
) : RecyclerView.Adapter<ViewPlanetHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewPlanetHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewPlanetHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewPlanetHolder, position: Int) =
        holder.bindView(list[position])

}