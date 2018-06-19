package com.daimler.tss.recyclerapp

import com.daimler.tss.recyclerapp.time.DataGeneration
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


/**
 * Created by Daimler TSS GmbH on 19.06.18 10:22.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
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
    fun sortBooksAscending() {
        val listOfBookItems = listOf(DataGeneration.getBookItem(), DataGeneration.getBookItem())
        val result = DataGeneration.sortBookItems(listOfBookItems, false)
        assertThat(result[0].publicationDate, lessThan(result[1].publicationDate))
    }

    @Test
    fun sortBooksDescending() {
        val listOfBookItems = listOf(DataGeneration.getBookItem(), DataGeneration.getBookItem())
        val result = DataGeneration.sortBookItems(listOfBookItems, true)
        assertThat(result[0].publicationDate, greaterThan(result[1].publicationDate))
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

}
