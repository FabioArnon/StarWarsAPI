package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.vehicle.Vehicles
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewVehicleHolder(
    private val view: View,
    private val onClick: (Vehicles) -> Unit
): RecyclerView.ViewHolder(view){
    fun bindView(item: Vehicles){
        view.setOnClickListener { onClick(item) }
        view.nameInsert.text = item.name
        view.segundoText.text = view.context.getString(R.string.model)
        view.segundoInsert.text = item.model
        view.terceiroText.text = view.context.getString(R.string.cost_in_credits)
        view.terceiroInsert.text = item.cost
        view.quartoText.text = view.context.getString(R.string.passengers)
        view.quartoInsert.text = item.passengers
    }
}
