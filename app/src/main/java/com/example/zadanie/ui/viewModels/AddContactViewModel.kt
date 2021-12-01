package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import kotlinx.coroutines.launch

class AddContactViewModel(private val repository: DataRepository) : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val public_key: MutableLiveData<String> = MutableLiveData()

    fun insertContact() {
        name.value?.let { nameIt ->
            if (nameIt.isNotEmpty()) {
                public_key.value?.let { public_keyIt ->
                    if (public_keyIt.isNotEmpty()) {
                        viewModelScope.launch { repository.insertContact(Contact(1, nameIt, public_keyIt)) }
                        name.postValue("")
                        public_key.postValue("")
                    }
                }
            }
        }
    }
}