package com.example.zadanie.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey()
    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "public_key")
    var public_key: String,
)