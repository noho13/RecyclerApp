package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.adapter.RecyclerViewAdapter

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:32.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
data class VerticalItem(val recyclerViewAdapter: RecyclerViewAdapter) : Item {

    override fun getLayoutId() = R.layout.vertical_item

}