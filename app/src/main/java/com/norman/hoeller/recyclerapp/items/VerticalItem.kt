package com.norman.hoeller.recyclerapp.items

import com.norman.hoeller.recyclerapp.R
import com.norman.hoeller.recyclerapp.adapter.RecyclerViewAdapter

/**
 * Created by Norman Hoeller
 */
data class VerticalItem(val recyclerViewAdapter: RecyclerViewAdapter) : Item {

    override fun getLayoutId() = R.layout.vertical_item

}