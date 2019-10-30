package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.vehicle.Vehicles
import com.example.starwarsapi.ui.holder.ViewVehicleHolder

class ListVehicleAdapter(
    val list: MutableList<Vehicles>,
    private val onClick: (Vehicles) -> Unit
): RecyclerView.Adapter<ViewVehicleHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewVehicleHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewVehicleHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewVehicleHolder, position: Int) = holder.bindView(list[position])
}