package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.specie.Species
import com.example.starwarsapi.ui.holder.ViewSpecieHolder

class ListSpecieAdapter(
    val list: MutableList<Species>,
    private val onClick: (Species) -> Unit
) :
    RecyclerView.Adapter<ViewSpecieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewSpecieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewSpecieHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewSpecieHolder, position: Int) =
        holder.bindView(list[position])
}