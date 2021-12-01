package com.example.zadanie.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "accId")
    var accId: Long,

    @ColumnInfo(name = "amount")
    var amount: String,

    @ColumnInfo(name = "public_key_sender")
    var public_key_sender: String,

    @ColumnInfo(name = "public_key_reciever")
    var public_key_reciever: String
) {
    constructor(
        accId: Long,
        amount: String,
        public_key_sender: String,
        public_key_reciever: String)
            : this(0, accId, amount, public_key_sender, public_key_reciever)
}