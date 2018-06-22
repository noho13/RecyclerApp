package com.norman.hoeller.recyclerapp.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.norman.hoeller.recyclerapp.R
import com.norman.hoeller.recyclerapp.items.Item
import java.util.*

@Entity
data class Book(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo val title: String,
        @ColumnInfo val description: String,
        @ColumnInfo val publicationDate: Date
) : Item {
    override fun getLayoutId() = R.layout.book_item
}
