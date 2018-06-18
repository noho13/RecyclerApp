package com.daimler.tss.recyclerapp.repo

import com.daimler.tss.recyclerapp.adapter.RecyclerViewAdapter
import com.daimler.tss.recyclerapp.items.BookItem
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.items.SectionItem
import com.daimler.tss.recyclerapp.items.VerticalItem

/**
 * Created by Daimler TSS GmbH on 18.06.18 15:31.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryRepo {

    fun getData(): List<Item> {
        val calendarWeekOne = SectionItem("week 23")
        val verticalItem = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"))))

        val calendarWeekTwo = SectionItem("week 24")
        val verticalItem1 = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"))))

        val calendarWeekThree = SectionItem("week 25")
        val verticalItem2 = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"))))

        val calendarWeekFour = SectionItem("week 26")
        val verticalItem3 = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"),
                BookItem("title 1", "description 1"))))

        val calendarWeekFive = SectionItem("week 27")
        val verticalItem4 = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"),
                BookItem("title 2", "description 2"))))

        val calendarWeekSix = SectionItem("week 28")
        val verticalItem5 = VerticalItem(RecyclerViewAdapter(listOf(BookItem("title 1", "description 1"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"),
                BookItem("title 3", "description 3"))))

        return listOf(calendarWeekOne, verticalItem, calendarWeekTwo, verticalItem1, calendarWeekThree, verticalItem2, calendarWeekFour, verticalItem3, calendarWeekFive, verticalItem4, calendarWeekSix, verticalItem5)
    }
}