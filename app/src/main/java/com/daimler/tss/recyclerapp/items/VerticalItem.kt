package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.adapter.HorizontalAdapter
import com.daimler.tss.recyclerapp.R

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:32.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
data class VerticalItem(val horizontalAdapter: HorizontalAdapter) : Item {

    override fun getId() = R.layout.vertical_item

}