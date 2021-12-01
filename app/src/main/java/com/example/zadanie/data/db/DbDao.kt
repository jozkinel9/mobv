package com.example.zadanie.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import java.security.PrivateKey

@Dao
interface DbDao {
    //  Accounts
    @Insert(entity = Account::class)
    suspend fun insertAccount(account: Account)

    @Delete
    suspend fun deleteWord(account: Account)

    @Query("SELECT * FROM accounts_table")
    fun getAccounts(): LiveData<List<Account>>

    @Query("SELECT * FROM accounts_table WHERE accId = :accIdLogged")
    fun getAccountById(accIdLogged: Long): Account

    @Query("SELECT balance FROM accounts_table WHERE public_key = :accountId")
    fun getBalance(accountId: String): String

    @Query("SELECT * FROM accounts_table WHERE private_key = :privateKey")
    fun getAccountByPrivateKey(privateKey: String): Account

    @Query("UPDATE accounts_table SET balance = :balance WHERE public_key = :accountId")
    fun updateBalance(accountId: String, balance: String)

    //  Contacts
    @Insert(entity = Contact::class)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts_table WHERE accId = :accIdLogged")
    fun getContacts(accIdLogged: Long): LiveData<List<Contact>>

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