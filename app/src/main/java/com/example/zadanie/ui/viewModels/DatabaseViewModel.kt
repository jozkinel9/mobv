package com.example.zadanie.ui.viewModels


import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DataRepository) : ViewModel() {
    val accounts: LiveData<List<Account>>
        get() = repository.getAccounts()

    //len funkcia na vypisanie textu
    val wordAsText: LiveData<String> = Transformations.map(accounts) {
        var text = ""
        it?.let {
            it.forEach { text += "${it.accId}, ${it.password}, ${it.private_key} \n" }
        }
        text
    }

    val inputText: MutableLiveData<String> = MutableLiveData()

    fun insertAccount() {
        inputText.value?.let {
            if (it.isNotEmpty()) {
                viewModelScope.launch { repository.insertAccount(Account(it, "bla", "bla", "bla")) }
                inputText.postValue("")
            }
        }
    }
}
