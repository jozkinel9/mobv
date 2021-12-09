package com.example.zadanie.data.db

import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.data.db.model.Transaction

class LocalCache(private val dao: DbDao) {
    //  Accounts
    suspend fun insertAccount(account: Account) {
        dao.insertAccount(account)
    }

    fun getAccounts(): LiveData<List<Account>> = dao.getAccounts()

    fun getAccountById(accIdLogged: Long): Account = dao.getAccountById(accIdLogged)

    fun getBalance(publicKey: String): String = dao.getBalance(publicKey)

    fun getBalanceById(accIdLogged: Long) = dao.getBalanceById(accIdLogged)

    fun getAccountByPrivateKey(privateKey: String): Account = dao.getAccountByPrivateKey(privateKey)

    fun updateBalance(accountId: String, balance: String) = dao.updateBalance(accountId, balance)

    //  Contacts
    suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    suspend fun insertTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    fun getContacts(accIdLogged: Long): LiveData<List<Contact>> = dao.getContacts(accIdLogged)

    fun getTransactions(publicKey: String): LiveData<List<Transaction>> = dao.getTransactions(publicKey)



//    suspend fun insertWords(accounts: List<Account>) {
//        dao.insertWords(accounts)
//    }
//
//    suspend fun insertWord(account: Account) {
//        dao.insertAccount(account)
//    }
//
//    suspend fun updateVideo(account: Account) {
//        dao.updateWord(account)
//    }
//
//    fun deleteWord(account: Account) {
//        GlobalScope.launch { dao.deleteWord(account) }
//    }
//
//    fun getWords(): LiveData<List<Account>> = dao.getWords()

}