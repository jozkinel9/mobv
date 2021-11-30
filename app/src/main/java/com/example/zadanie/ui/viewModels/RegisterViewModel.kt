package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.doAsync
import kotlinx.coroutines.launch
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server
import java.net.URL
import java.util.*

class RegisterViewModel(private val repository: DataRepository) : ViewModel() {
    var code: MutableLiveData<String> = MutableLiveData()

    fun createStellarUser(){
        val pair = KeyPair.random()

        // TODO - ulozit pair do nasej DB ku kodu ktory zadal uzivatel
//        privateKey = pair.accountId
//        publicKey = pair.publicKey.toString()

        doAsync {
            val friendbotUrl = java.lang.String.format(
                "https://friendbot.stellar.org/?addr=%s",
                pair.accountId
            )
            val response = URL(friendbotUrl).openStream()
            val body = Scanner(response, "UTF-8").useDelimiter("\\A").next()
            println("SUCCESS! You have a new account :)\n$body")
        }

        doAsync {
            val server = Server("https://horizon-testnet.stellar.org")
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
        insertAccountIntoDb(pair)
    }

    private fun insertAccountIntoDb(pair: KeyPair) {
        System.out.printf("funkcia zavolana")
        code.value?.let {
            if (it.isNotEmpty()) {
                System.out.printf("UUUUUUSPPEEEEEEEEEECHHH")
                viewModelScope.launch { repository.insertAccount(Account("bla", it, pair.publicKey.toString(), pair.accountId)) }
                System.out.printf("UUUUUUSPPEEEEEEEEEECHHH")
            }
        }
    }

    private fun fundAccount(pair: KeyPair){

    }
}