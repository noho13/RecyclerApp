package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R

/**
 * Created by Norman Hoeller
 */
class SectionItem(val calendarWeek: String) : Item {

    override fun getLayoutId() = R.layout.section_item

}