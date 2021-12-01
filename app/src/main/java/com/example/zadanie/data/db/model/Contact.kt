package com.example.zadanie.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "accId")
    var accId: Long,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "public_key_reciever")
    var public_key_reciever: String,
) {
    constructor(
        accId: Long,
        name: String,
        public_key_reciever: String)
            : this(0, accId, name, public_key_reciever)
}