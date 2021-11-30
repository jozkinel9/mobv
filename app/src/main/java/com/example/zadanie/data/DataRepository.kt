package com.example.zadanie.data

import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.LocalCache
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact

/**
 * Repository class that works with local and remote data sources.
 */
class DataRepository private constructor(
    private val cache: LocalCache
) {

    companion object {
        const val TAG = "DataRepository"
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(cache: LocalCache): DataRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: DataRepository(cache).also { INSTANCE = it }
            }
    }

//  Accounts
    fun getAccounts(): LiveData<List<Account>> = cache.getAccounts()

    suspend fun insertAccount(account: Account) {
        cache.insertAccount(account)
    }

//  Contacts
    suspend fun insertContact(contact: Contact) {
        cache.insertContact(contact)
    }

    fun getContacts(): LiveData<List<Contact>> = cache.getContacts()


}
