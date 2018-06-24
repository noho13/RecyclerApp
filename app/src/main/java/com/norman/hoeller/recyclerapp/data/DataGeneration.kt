package com.norman.hoeller.recyclerapp.data

import android.support.annotation.VisibleForTesting
import com.norman.hoeller.recyclerapp.adapter.RecyclerViewAdapter
import com.norman.hoeller.recyclerapp.db.Book
import com.norman.hoeller.recyclerapp.items.Item
import com.norman.hoeller.recyclerapp.items.SectionItem
import com.norman.hoeller.recyclerapp.items.VerticalItem
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Norman Hoeller
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

    @VisibleForTesting
    fun randomDate(): Date {
        val random = ThreadLocalRandom.current().nextLong(startDate.time, endDate.time)
        return Date(random)
    }

    internal fun getBookItem() = Book(title = "book title",
            description =  "book description",
            publicationDate =  randomDate())

    @VisibleForTesting
    fun getCalendarWeekForDate(date: Date): String {
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
