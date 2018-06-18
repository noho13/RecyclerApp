package com.daimler.tss.recyclerapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.adapter.HorizontalAdapter
import com.daimler.tss.recyclerapp.adapter.VerticalAdapter
import com.daimler.tss.recyclerapp.items.BookItem
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.items.SectionItem
import com.daimler.tss.recyclerapp.items.VerticalItem
import kotlinx.android.synthetic.main.fr_main.*

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:05.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val verticalAdapter = VerticalAdapter(getData())
        rv_vertical.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = verticalAdapter
        }
    }

    private fun getData(): List<Item> {

        val calendarWeekOne = SectionItem("week 23")
        val verticalItem = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"))))

        val calendarWeekTwo = SectionItem("week 24")
        val verticalItem1 = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"))))

        val calendarWeekThree = SectionItem("week 25")
        val verticalItem2 = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"))))

        val calendarWeekFour = SectionItem("week 26")
        val verticalItem3 = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"))))

        val calendarWeekFive = SectionItem("week 27")
        val verticalItem4 = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"))))

        val calendarWeekSix = SectionItem("week 28")
        val verticalItem5 = VerticalItem(HorizontalAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"))))

        return listOf(calendarWeekOne, verticalItem, calendarWeekTwo, verticalItem1, calendarWeekThree, verticalItem2, calendarWeekFour, verticalItem3, calendarWeekFive, verticalItem4, calendarWeekSix, verticalItem5)
    }

    companion object {
        fun createInstance() = MainFragment()
    }

}