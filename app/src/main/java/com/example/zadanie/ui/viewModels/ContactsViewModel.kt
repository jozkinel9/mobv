package com.example.zadanie.ui.viewModels

import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Contact

class ContactsViewModel(private val repository: DataRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>>
        get() = repository.getContacts()

    val contactsAsText: LiveData<String> = Transformations.map(contacts) {
        var text = ""
        it?.let {
            it.forEach { text += "${it.name}, ${it.public_key}\n" }
        }
        text
    }

}