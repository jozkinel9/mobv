package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.db.model.Transaction
import com.example.zadanie.data.DataRepository

class TransactionsViewModel(private val repository: DataRepository) : ViewModel() {
    val transactions: LiveData<List<Transaction>>
        get() = repository.getTransactions(repository.getLoggedUser().getAccId())

    val transactionsAsText: LiveData<String> = Transformations.map(transactions) {
        var text = ""
        it?.let {
            it.forEach { text += "Sender: ${it.public_key_sender}, Receiver: ${it.public_key_reciever}, Amount: ${it.amount}\n" }
        }
        text
    }
}