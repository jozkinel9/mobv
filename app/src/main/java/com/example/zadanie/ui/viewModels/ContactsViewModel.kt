package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Contact

class ContactsViewModel(private val repository: DataRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>>
        get() = repository.getContacts(repository.getLoggedUser().getAccId())

    val contactsAsText: LiveData<String> = Transformations.map(contacts) {
        var text = ""
        it?.let {
            it.forEach { text += "${it.accId}, ${it.name}, ${it.public_key_reciever}\n" }
        }
        text
    }
    var bla = repository.getLoggedUser(). let { Log.e("LoggedUser", "done: ${it.getAccId()}    ${it.getPin()}") }


}