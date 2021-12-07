package com.example.zadanie.data

import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.LocalCache
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.data.db.model.Transaction
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server
import java.net.URL
import java.util.*


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
        private lateinit var loggedUser: LoggedUser

        fun getInstance(cache: LocalCache): DataRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: DataRepository(cache).also { INSTANCE = it }
            }

//        fun getLoggetUser() : LoggedUser {
//            return loggedUser
//        }
//
//        fun setLoggedUser(accId: String, pin: String) {
//            this.loggedUser = LoggedUser(accId, pin)
//        }
    }

    fun setLoggedUser(accId: Long, pin: String) {
        loggedUser = LoggedUser(accId, pin)
    }

    fun getLoggedUser() : LoggedUser {
        return loggedUser
    }

    //    Create account on Stellar
    fun createStellarAccount(pair: KeyPair) {
        val server = Server("https://horizon-testnet.stellar.org")

        val friendbotUrl = String.format(
            "https://friendbot.stellar.org/?addr=%s",
            pair.accountId
        )
        val response = URL(friendbotUrl).openStream()
        val body = Scanner(response, "UTF-8").useDelimiter("\\A").next()
        println("SUCCESS! You have a new account :)\n$body")


        val account = server.accounts().account(pair.accountId)
        System.out.println("Balances for account " + pair.accountId)
        for (balance in account.balances) {
            System.out.printf(
                "Type: %s, Code: %s, Balance: %s%n",
                balance.assetType,
                balance.assetCode,
                balance.balance
            )
        }

    }

    fun updateBalance(accountId: String): String {
        val server = Server("https://horizon-testnet.stellar.org/")
        val account = server.accounts().account(accountId)

        for (balance in account.balances) {
            cache.updateBalance(accountId, balance.balance)
            return balance.balance
        }

        return ""
    }


    //  Accounts
    fun getAccounts(): LiveData<List<Account>> = cache.getAccounts()

    suspend fun insertAccount(account: Account) = cache.insertAccount(account)

    fun getAccountById(accIdLogged: Long): Account = cache.getAccountById(accIdLogged)

    fun getAccountByPrivateKey(privateKey: String): Account =
        cache.getAccountByPrivateKey(privateKey)

    fun getBalance(publicKey: String): String = cache.getBalance(publicKey)

    fun getBalanceById(accIdLogged: Long): String = cache.getBalanceById(accIdLogged)


    //  Contacts
    suspend fun insertContact(contact: Contact) {
        cache.insertContact(contact)
    }

    suspend fun insertTransaction(transaction: Transaction) {
        cache.insertTransaction(transaction)
    }

    fun getContacts(accIdLogged: Long): LiveData<List<Contact>> = cache.getContacts(accIdLogged)

    fun getTransactions(accIdLogged: Long): LiveData<List<Transaction>> = cache.getTransactions(accIdLogged)
}
