package com.example.zadanie.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.doAsync
import kotlinx.coroutines.launch
import org.stellar.sdk.Server

class AddAccountViewModel (private val repository: DataRepository) : ViewModel() {
    val private_key: MutableLiveData<String> = MutableLiveData()
    val public_key: MutableLiveData<String> = MutableLiveData()

    //TODO neotestovane ci to funguje, alebo dat raci cele do async? netusim
    fun insertAccount() {
        doAsync {
            val server = Server("https://horizon-testnet.stellar.org")
            server.accounts().account(public_key.value)
//            var a = repository.isValidStellarAccount(public_key.toString(), private_key.toString())
        }
        private_key.value?.let { private_keyIt ->
            if (private_keyIt.isNotEmpty()) {
                public_key.value?.let { public_keyIt ->
                    if (public_keyIt.isNotEmpty()) {
                        viewModelScope.launch { repository.insertAccount(Account(public_keyIt, private_keyIt)) }
                        private_key.postValue("")
                        public_key.postValue("")
                    }
                }
            }
        }
    }
}