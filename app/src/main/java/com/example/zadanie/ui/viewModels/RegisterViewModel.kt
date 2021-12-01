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
    val generatedPublicKey: MutableLiveData<String> = MutableLiveData()
    val generatedPrivateKey: MutableLiveData<String> = MutableLiveData()

    fun createStellarUser(){
        val pair = KeyPair.random()
        doAsync {
            repository.createStellarAccount(pair)
        }

        viewModelScope.launch { repository.insertAccount(Account(public_key = pair.accountId, private_key = pair.secretSeed.joinToString(""), balance = "" )) }

        generatedPublicKey.value = pair.accountId
        generatedPrivateKey.value = pair.secretSeed.joinToString("")
    }
}