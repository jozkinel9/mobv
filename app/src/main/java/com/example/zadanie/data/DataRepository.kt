package com.example.zadanie.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.LocalCache
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server
import java.net.URL
import java.util.*
import org.stellar.sdk.requests.ErrorResponse
import org.stellar.sdk.Transaction
import org.stellar.sdk.responses.SubmitTransactionResponse
import java.lang.Exception


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

    //TODO nejak to osetrit ze ci taky naozaj existuje a vratit bud true alebo false
    fun isValidStellarAccount(publicKey: String, privateKey: String) :Boolean {
        val server = Server("https://horizon-testnet.stellar.org")
        try {
            server.accounts()
                .account(publicKey)
            return true
        } catch (e: ErrorResponse) {
            if (e.code == 404) {
                Log.e("Stellar response", "error, ucet neexistuje")
            }
            throw e
        }
    }


    fun makeTransfer(transaction: Transaction) {
        val server = Server("https://horizon-testnet.stellar.org")
        try {
            val response: SubmitTransactionResponse = server.submitTransaction(transaction)
            println("Success!")
            println(response)
        } catch (e: Exception) {
            println("Something went wrong!")
            println(e.message)
            // If the result is unknown (no response body, timeout etc.) we simply resubmit
            // already built transaction:
            // SubmitTransactionResponse response = server.submitTransaction(transaction);
        }
    }





    //  Accounts
    fun getAccounts(): LiveData<List<Account>> = cache.getAccounts()

    suspend fun insertAccount(account: Account) {
        cache.insertAccount(account)
    }

    fun getAccountById(accIdLogged: Long): LiveData<Account> = cache.getAccountById(accIdLogged)

    fun getAccountByPrivateKey(privateKey: String): LiveData<List<Account>> = cache.getAccountByPrivateKey(privateKey)


    //  Contacts
    suspend fun insertContact(contact: Contact) {
        cache.insertContact(contact)
    }

    fun getContacts(accIdLogged: Long): LiveData<List<Contact>> = cache.getContacts(accIdLogged)


}
