package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.models.people.People
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewPeopleHolder(
    private val view: View,
    private val onClick: (People) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindView(item: People) {
        view.setOnClickListener { onClick(item) }
        view.nameInsert.text = item.name
        view.segundoInsert.text = item.gender
        view.terceiroInsert.text = item.height
        view.quartoInsert.text = item.mass
    }
}