package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.planet.Planets
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewPlanetHolder(
    private val view: View,
    private val onClick: (Planets) -> Unit
    ): RecyclerView.ViewHolder(view){
    fun bindView(item: Planets){
        view.setOnClickListener { onClick(item) }
        view.nameInsert.text = item.name
        view.segundoText.text = view.context.getString(R.string.diameter)
        view.segundoInsert.text = item.diameter
        view.terceiroText.text = view.context.getString(R.string.orbital_period)
        view.terceiroInsert.text = item.orbitalPeriod
        view.quartoText.text = view.context.getString(R.string.population)
        view.quartoInsert.text = item.population
    }
}
