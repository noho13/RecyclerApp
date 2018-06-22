package com.daimler.tss.recyclerapp.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.db.Book
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.items.SectionItem
import com.daimler.tss.recyclerapp.items.VerticalItem
import kotlinx.android.synthetic.main.book_item.view.*
import kotlinx.android.synthetic.main.section_item.view.*
import kotlinx.android.synthetic.main.vertical_item.view.*

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:22.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class RecyclerViewAdapter(var data: List<Item> = listOf()) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        if (view is RecyclerView) {
            view.recycledViewPool = viewPool
        }
        return ItemViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.bindItem(item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return item.getLayoutId()
    }

    class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bindItem(item: Item): Unit = with(itemView) {
            when (item.getLayoutId()) {
                R.layout.vertical_item -> rv_horizontal.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = (item as VerticalItem).recyclerViewAdapter
                    setHasFixedSize(true)
                }
                R.layout.section_item -> tv_section.text = (item as SectionItem).calendarWeek
                else -> {
                    tv_title.text = (item as Book).title
                    tv_description.text = item.description + " " + item.publicationDate
                }
            }
        }
    }
}