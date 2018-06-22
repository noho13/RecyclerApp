package com.daimler.tss.recyclerapp.items

import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.adapter.RecyclerViewAdapter

/**
 * Created by Norman Hoeller
 */
data class VerticalItem(val recyclerViewAdapter: RecyclerViewAdapter) : Item {

    override fun getLayoutId() = R.layout.vertical_item

}