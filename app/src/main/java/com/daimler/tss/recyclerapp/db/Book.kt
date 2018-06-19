package com.daimler.tss.recyclerapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.daimler.tss.recyclerapp.R
import com.daimler.tss.recyclerapp.items.Item

@Entity
data class Book(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo val title: String,
        @ColumnInfo val description: String,
        @ColumnInfo val publicationDate: String
) : Item {
    override fun getLayoutId() = R.layout.book_item
}
