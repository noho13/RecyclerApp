package com.daimler.tss.recyclerapp.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.db.Book
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.items.SectionItem
import com.daimler.tss.recyclerapp.items.VerticalItem

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:22.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class RecyclerViewAdapter(private val data: List<Item>) : RecyclerView.Adapter<RecyclerViewAdapter.VerticalViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        if (view is RecyclerView) {
            view.recycledViewPool = viewPool
        }
        return VerticalViewHolder(view, viewType)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val item = data[position]
        holder.bindItem(item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return item.getLayoutId()
    }

    class VerticalViewHolder(item: View, viewType: Int) : RecyclerView.ViewHolder(item) {

        private lateinit var carousel: RecyclerView
        private lateinit var headerText: TextView
        private lateinit var bookTitle: TextView
        private lateinit var bookDescription: TextView

        init {
            when (viewType) {
                R.layout.vertical_item -> carousel = item.findViewById(R.id.rv_horizontal)
                R.layout.section_item -> headerText = item.findViewById(R.id.tv_section)
                else -> {
                    bookTitle = item.findViewById(R.id.tv_title)
                    bookDescription = item.findViewById(R.id.tv_description)
                }
            }
        }

        fun bindItem(item: Item) {
            when {
                item.getLayoutId() == R.layout.vertical_item -> carousel.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = (item as VerticalItem).recyclerViewAdapter
                    setHasFixedSize(true)
                }
                item.getLayoutId() == R.layout.section_item -> headerText.text = (item as SectionItem).calendarWeek
                else -> {
                    bookTitle.text = (item as Book).title
                    bookDescription.text = item.description + " " + item.publicationDate
                }
            }
        }
    }
}