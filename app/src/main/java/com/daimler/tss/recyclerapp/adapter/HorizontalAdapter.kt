package com.daimler.tss.recyclerapp.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.items.BookItem
import kotlinx.android.synthetic.main.book_item.view.*

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:39.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class HorizontalAdapter(private val books: List<BookItem>) : RecyclerView.Adapter<HorizontalAdapter.BookItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookItemViewHolder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val bookItem = books[position]
        holder.bind(bookItem)
    }

    class BookItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bookCard = view.findViewById<CardView>(R.id.book_card)
        private val bookTitle = bookCard.tv_title
        private val bookDescription = bookCard.tv_description

        fun bind(item: BookItem) {
            bookTitle.text = item.title
            bookDescription.text = item.description
        }
    }
}