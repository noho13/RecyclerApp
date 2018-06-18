package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R

/**
 * Created by Daimler TSS GmbH on 18.06.18 14:59.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class SectionItem(val calendarWeek: String) : Item {

    override fun getId() = R.layout.section_item

}