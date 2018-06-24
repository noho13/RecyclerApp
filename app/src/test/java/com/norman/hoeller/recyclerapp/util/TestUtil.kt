package com.norman.hoeller.recyclerapp.util

import com.norman.hoeller.recyclerapp.data.Resource
import com.norman.hoeller.recyclerapp.db.Book
import com.norman.hoeller.recyclerapp.items.Item
import org.mockito.Mockito
import java.util.*

fun getBookForWeek(week: Int): Book {
    return Book(title = "foo", description = "bar", publicationDate = getDateForWeek(week))
}

private fun getDateForWeek(week: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, 2018)
    calendar.set(Calendar.MONTH, 0)
    calendar.set(Calendar.WEEK_OF_YEAR, week)
    return calendar.time
}

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

fun successData(): Resource<List<Item>>? {
    return Resource.success(listOf(getBookForWeek(2), getBookForWeek(3)))
}