package com.daimler.tss.recyclerapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.adapter.RecyclerViewAdapter
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.viewmodel.LibraryViewModel
import kotlinx.android.synthetic.main.fr_main.*

/**
 * Created by Daimler TSS GmbH on 18.06.18 13:05.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var recyclerViewAdapter: RecyclerViewAdapter
        val libViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        libViewModel.data.observe(this, Observer { data: List<Item>? ->
            data?.let {
                recyclerViewAdapter = RecyclerViewAdapter(data)
                rv_vertical.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = recyclerViewAdapter
                }
            }
        })
        libViewModel.loadData()
    }

    companion object {
        fun createInstance() = LibraryFragment()
    }

}