package com.example.zadanie.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Accounts_table")
data class Account(
    @PrimaryKey(autoGenerate = true)
    var accId: Long,

    @ColumnInfo(name = "public_key")
    var public_key: String,

    @ColumnInfo(name = "private_key")
    var private_key: String
) {
    constructor(
        public_key: String,
        private_key: String)
            : this(0, public_key, private_key)
}

/*
Entity(tableName = "accounts_table")
data class Account {
    @PrimaryKey(autoGenerate = true)
    var accId: Long = 0L

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    @ColumnInfo(name = "public_key")
    var public_key: String = ""

    @ColumnInfo(name = "private_key")
    var private_key: String = ""

    public Account(email: String) {
    }
}
 */