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


//  Contacts
    suspend fun insertContact(contact: Contact) {
    dao.insertContact(contact)
}
    fun getContacts(): LiveData<List<Contact>> = dao.getContacts()



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