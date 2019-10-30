package com.example.starwarsapi.application.xt

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.concurrent.schedule

fun RecyclerView.onScrollListener(onScroll: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            onScroll()
        }
    })
}

fun SearchView.onSearchDelayedOrCanceledListener(delay: Long = 500, onSearch: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            Timer().schedule(delay) {
                onSearch(query)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean = false
    })

    setOnCloseListener {
        onSearch("")
        false
    }


}
