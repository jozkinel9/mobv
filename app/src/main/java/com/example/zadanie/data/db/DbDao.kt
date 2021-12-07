package com.example.zadanie.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.data.db.model.Transaction

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

    @Query("SELECT balance FROM accounts_table WHERE public_key = :publicKey")
    fun getBalance(publicKey: String): String

    @Query("SELECT balance FROM accounts_table WHERE accId = :accIdLogged")
    fun getBalanceById(accIdLogged: Long): String

    @Query("SELECT * FROM accounts_table WHERE private_key = :privateKey")
    fun getAccountByPrivateKey(privateKey: String): Account

    @Query("UPDATE accounts_table SET balance = :balance WHERE public_key = :accountId")
    fun updateBalance(accountId: String, balance: String)

    //  Contacts
    @Insert(entity = Contact::class)
    suspend fun insertContact(contact: Contact)

    @Insert(entity = Transaction::class)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM contacts_table WHERE accId = :accIdLogged")
    fun getContacts(accIdLogged: Long): LiveData<List<Contact>>

    @Query("SELECT * FROM transactions_table WHERE public_key_sender = :accIdLogged OR public_key_reciever = :accIdLogged")
    fun getTransactions(accIdLogged: Long): LiveData<List<Transaction>>

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