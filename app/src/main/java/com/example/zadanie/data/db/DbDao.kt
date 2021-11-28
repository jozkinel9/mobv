package com.example.zadanie.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact

@Dao
interface DbDao {
//  Accounts
    @Insert(entity = Account::class)
        //"INSERT INTO accounts_table (email, password, public_key, private_key) VALUES (:account)")
    suspend fun insertAccount(account: Account)

    @Delete
    suspend fun deleteWord(account: Account)

    @Query("SELECT * FROM accounts_table")
    fun getAccounts(): LiveData<List<Account>>


//  Contacts
    @Insert(entity = Contact::class)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts_table")
    fun getContacts(): LiveData<List<Contact>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWords(accounts: List<Account>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWord(account: Account)
//
//    @Update
//    suspend fun updateWord(account: Account)
//
//    @Delete
//    suspend fun deleteWord(account: Account)
//
//    @Query("SELECT * FROM words")
//    fun getWords(): LiveData<List<Account>>



}