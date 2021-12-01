package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.doAsync
import kotlinx.coroutines.launch
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server
import java.lang.Exception

class AddAccountViewModel (private val repository: DataRepository) : ViewModel() {
    val private_key: MutableLiveData<String> = MutableLiveData()
    val notification: MutableLiveData<String> = MutableLiveData()
    val exists = false

    fun insertAccount() {
        doAsync {
            val server = Server("https://horizon-testnet.stellar.org")

            try {
                val keyPair = KeyPair.fromSecretSeed(private_key.value)
                server.accounts().account(keyPair.accountId)

                viewModelScope.launch { repository.insertAccount(Account(public_key = keyPair.accountId.toString(), private_key = keyPair.secretSeed.joinToString(""), balance = "")) }
                private_key.postValue("")
                notification.postValue("Success")
            } catch (e: Exception) {
                notification.postValue("Stellar account doesnt exists")
            }
        }
    }
}