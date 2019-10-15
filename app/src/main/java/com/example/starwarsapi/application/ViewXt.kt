package com.example.starwarsapi.application

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollListener(onScroll:() -> Unit){
    addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            onScroll()
        }
    })
}