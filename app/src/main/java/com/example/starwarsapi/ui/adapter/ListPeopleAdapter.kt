package com.example.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.ui.holder.ViewPeopleHolder

class ListPeopleAdapter(
    val list: MutableList<People>,
    private val onClick: (People) -> Unit
) :
    RecyclerView.Adapter<ViewPeopleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewPeopleHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewPeopleHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewPeopleHolder, position: Int) =
        holder.bindView(list[position])
}