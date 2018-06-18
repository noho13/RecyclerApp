package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R

data class BookItem(
        val title: String,
        val description: String
) : Item {
    override fun getId() = R.layout.book_item
}
