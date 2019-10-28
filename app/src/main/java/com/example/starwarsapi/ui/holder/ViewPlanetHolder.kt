package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.models.Planets
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewPlanetHolder(
    private val view: View,
    private val onClick: (Planets) -> Unit
    ): RecyclerView.ViewHolder(view){
    fun bindView(item: Planets){
        view.setOnClickListener { onClick(item) }
        view.nameInsert.text = item.name
        view.segundoText.text = "diameter: "
        view.segundoInsert.text = item.diameter
        view.terceiroText.text = "orbitalPeriod: "
        view.terceiroInsert.text = item.orbitalPeriod
        view.quartoText.text = "population: "
        view.quartoInsert.text = item.population
    }
}
