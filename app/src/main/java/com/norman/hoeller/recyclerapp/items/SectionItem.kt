package com.norman.hoeller.recyclerapp.items

import com.norman.hoeller.recyclerapp.R

/**
 * Created by Norman Hoeller
 */
class SectionItem(val calendarWeek: String) : Item {

    override fun getLayoutId() = R.layout.section_item

}