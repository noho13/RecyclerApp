package com.norman.hoeller.recyclerapp

import com.norman.hoeller.recyclerapp.data.DataGeneration
import com.norman.hoeller.recyclerapp.db.Book
import com.norman.hoeller.recyclerapp.items.SectionItem
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


/**
 * Created by Norman Hoeller
 */
@RunWith(JUnit4::class)
class TestDataGeneration {


    private lateinit var startDate: Date
    private lateinit var endDate: Date

    @Before
    fun setup() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        startDate = calendar.time
        calendar.set(Calendar.MONTH, 11)
        endDate = calendar.time
    }


    @Test
    fun randomDateGenerator_shouldGenerateDatesBetweenStartDateAndEndDate() {
        val date = DataGeneration.randomDate()
        assertThat(date.time, greaterThan(startDate.time))
        assertThat(date.time, lessThan(endDate.time))
    }


    @Test
    fun calendarWeekForDate() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.WEEK_OF_YEAR, 3)
        val dateWithinThirdWeek = calendar.time
        val weekForDate = DataGeneration.getCalendarWeekForDate(dateWithinThirdWeek)
        assertThat(weekForDate, `is`("Week 3"))
    }

    @Test
    fun addSectionHeaderToList() {
        val sortedListOfBooks = listOf(getBookForWeek(2),
                getBookForWeek(3))
                .sortedBy { it.publicationDate }

        val sectionedList = DataGeneration.addSectionHeadersToList(sortedListOfBooks)
        val sectionItemWeek2 = sectionedList[0]
        assertThat(sectionItemWeek2, instanceOf(SectionItem::class.java))
        assertThat((sectionItemWeek2 as SectionItem).calendarWeek, `is`("Week 2"))
        val sectionItemWeek3 = sectionedList[2]
        assertThat((sectionItemWeek3 as SectionItem).calendarWeek, `is`("Week 3"))
    }

    private fun getDateForWeek(week: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2018)
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.WEEK_OF_YEAR, week)
        return calendar.time
    }

    private fun getBookForWeek(week: Int): Book {
        return Book(title = "foo", description = "bar", publicationDate = getDateForWeek(week))
    }

}
