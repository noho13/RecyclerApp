package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R
import java.util.*

data class BookItem(
        val title: String,
        val description: String,
        val publicationDate: Date
) : Item {
    override fun getId() = R.layout.book_item
}
