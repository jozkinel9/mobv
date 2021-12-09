package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.db.model.Transaction
import com.example.zadanie.data.DataRepository
import com.example.zadanie.doAsync

class TransactionsViewModel(private val repository: DataRepository) : ViewModel() {
    var transactions: LiveData<List<Transaction>> = MutableLiveData()

    val transactionsAsText: LiveData<String> = Transformations.map(transactions) {
        var text = ""
        it?.let {
            it.forEach { text += "Sender: ${it.public_key_sender}, Receiver: ${it.public_key_reciever}, Amount: ${it.amount}\n" }
        }
        text
    }

    fun setTransactions() {
        doAsync {
            transactions = repository.getTransactions(repository.getAccountById(repository.getLoggedUser().getAccId()).public_key)
        }
    }
}