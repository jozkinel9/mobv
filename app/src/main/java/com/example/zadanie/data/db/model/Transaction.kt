package com.example.zadanie.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey()
    var accId: Long,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "amount")
    var amount: String,

    @ColumnInfo(name = "public_key_reciever")
    var public_key_reciever: String,

    @ColumnInfo(name = "public_key_sender")
    var public_key_sender: String
)