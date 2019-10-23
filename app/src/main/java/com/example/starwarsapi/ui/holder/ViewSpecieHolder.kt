package com.example.starwarsapi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.models.Species
import kotlinx.android.synthetic.main.item_adapter_list.view.*

class ViewSpecieHolder(
    private val view: View,
    private val onClick: (Species) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun bindView(item: Species) {
        view.setOnClickListener { onClick(item) }
        view.nameInsert.text = item.name
        view.segundoText.text = "classification:"
        view.segundoInsert.text = item.classification
        view.terceiroText.text = "language:"
        view.terceiroInsert.text = item.language
        view.quartoText.text = "average lifespan:"
        view.quartoInsert.text = item.averageLifespan
    }
}
