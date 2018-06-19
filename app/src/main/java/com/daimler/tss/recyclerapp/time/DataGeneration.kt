package com.daimler.tss.recyclerapp.time

import com.daimler.tss.recyclerapp.adapter.RecyclerViewAdapter
import com.daimler.tss.recyclerapp.db.Book
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.items.SectionItem
import com.daimler.tss.recyclerapp.items.VerticalItem
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Daimler TSS GmbH on 19.06.18 10:18.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */

object DataGeneration {

    private var startDate: Date
    private var endDate: Date

    init {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 7)
        startDate = calendar.time
        calendar.set(Calendar.MONTH, 9)
        endDate = calendar.time
    }

    fun randomDate(): String {
        val random = ThreadLocalRandom.current().nextLong(startDate.time, endDate.time)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(random))
    }

    fun sortBookItems(items: List<Book>, descending: Boolean = false): List<Book> {
        return if (descending) {
            items.sortedByDescending { it.publicationDate }
        } else {
            items.sortedBy { it.publicationDate }
        }
    }

    fun getBookItem() = Book(null, "booktitle", "book description", randomDate())

    fun getBooks(): MutableList<Book> {
        return MutableList(40) { getBookItem() }
    }

    fun getCalendarWeekForDate(dateAsString: String): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(dateAsString)
        val cal = Calendar.getInstance()
        cal.time = date
        val week = cal.get(Calendar.WEEK_OF_YEAR)
        return "Week $week"
    }

    fun addSectionHeadersToList(sortedList: List<Book>): List<Item> {
        val booksWithSections = mutableListOf<Item>()
        var section = mutableListOf<Item>()
        var currentElementDate = sortedList[0].publicationDate
        var currentHeader = getCalendarWeekForDate(currentElementDate)
        booksWithSections.add(SectionItem(currentHeader))

        for (book in sortedList) {
            if (getCalendarWeekForDate(book.publicationDate) == currentHeader) {
                section.add(book)
            } else {
                booksWithSections.add(VerticalItem(RecyclerViewAdapter(section)))
                currentElementDate = book.publicationDate
                currentHeader = getCalendarWeekForDate(currentElementDate)
                booksWithSections.add(SectionItem(currentHeader))
                section = mutableListOf()
                section.add(book)
            }
        }
        booksWithSections.add(VerticalItem(RecyclerViewAdapter(section)))
        return booksWithSections
    }
}