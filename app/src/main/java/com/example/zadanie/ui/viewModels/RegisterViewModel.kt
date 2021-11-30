package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie.doAsync
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server
import java.net.URL
import java.util.*

class RegisterViewModel : ViewModel() {
    var code: MutableLiveData<String> = MutableLiveData()
    var privateKey: MutableLiveData<String> = MutableLiveData()
    var publicKey: MutableLiveData<String> = MutableLiveData()

    fun registerUser(){
        Log.e("code", this.code.value.toString())
        Log.e("privateKey", this.privateKey.value.toString())
        Log.e("publicKey", this.publicKey.value.toString())


    }

    fun createStellarUser(){
        val pair = KeyPair.random()

        // TODO - ulozit pair do nasej DB ku kodu ktory zadal uzivatel
        println(pair.accountId)
        println(pair.publicKey)

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
    }

    private fun fundAccount(pair: KeyPair){

    }
}