package com.daimler.tss.recyclerapp.repo

import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.time.DataGeneration


/**
 * Created by Daimler TSS GmbH on 18.06.18 15:31.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryRepo {

    fun getData(): List<Item> {
        val list = DataGeneration.getBooks()
        val orderedList = DataGeneration.sortBookItems(list)
        return DataGeneration.addSectionHeadersToList(orderedList)
    }

}