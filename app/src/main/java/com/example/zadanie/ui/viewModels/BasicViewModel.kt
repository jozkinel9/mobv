package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.DataRepository
import com.example.zadanie.doAsync

class BasicViewModel(private val repository: DataRepository) : ViewModel() {
    val balance: MutableLiveData<String> = MutableLiveData<String>()
    var loggedUserKey: String = "";

    fun getUserKey() {
        doAsync {
            Log.e("kokot", repository.getLoggedUser().getAccId().toString())
            loggedUserKey = repository.getLoggedUser().getAccId()?.let { repository.getAccountById(it).public_key }!!
        }
    }

    fun updateAmount() {
        getUserKey()

        doAsync {
            Log.e("updating", balance.value.toString())

            balance.postValue(repository.getBalance(loggedUserKey))
        }
    }
}