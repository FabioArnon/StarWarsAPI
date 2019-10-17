package com.example.starwarsapi.ui
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.models.People
import kotlinx.android.synthetic.main.item_adapter_list.view.*

 class ListAdapter<T> (
    val list: MutableList<T>,
    private val configureView: (T, view: View)-> Unit
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)
        return ViewHolder(configureView,view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) = holder.bindView(list[position])

     class ViewHolder<B>(
         private val configureView: (B, view: View)-> Unit,
         private val view: View
    ) : RecyclerView.ViewHolder(view) {
         fun bindView(item: B) = configureView(item, view)
        }
    }
