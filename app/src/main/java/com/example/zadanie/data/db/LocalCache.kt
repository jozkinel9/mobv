package com.example.zadanie.data.db

import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact

class LocalCache(private val dao: DbDao) {
    //  Accounts
    suspend fun insertAccount(account: Account) {
        dao.insertAccount(account)
    }
    fun getAccounts(): LiveData<List<Account>> = dao.getAccounts()

    fun getAccountById(accIdLogged: Long): Account = dao.getAccountById(accIdLogged)

    fun getBalance(accountId: String): String = dao.getBalance(accountId)

    fun getAccountByPrivateKey(privateKey: String): Account = dao.getAccountByPrivateKey(privateKey)

    fun updateBalance(accountId: String, balance: String) = dao.updateBalance(accountId, balance)

    //  Contacts
    suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    fun getContacts(accIdLogged: Long): LiveData<List<Contact>> = dao.getContacts(accIdLogged)



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