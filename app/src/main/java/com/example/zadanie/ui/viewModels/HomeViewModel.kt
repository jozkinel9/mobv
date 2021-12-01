package com.example.zadanie.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.zadanie.R
import com.example.zadanie.data.DataRepository
import com.example.zadanie.doAsync
import kotlinx.coroutines.delay

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    var loggedUserId: Long = 0;
    val inPin: MutableLiveData<String> = MutableLiveData()
    val privateKey: MutableLiveData<String> = MutableLiveData()

    fun checkUserPin(navController: NavController) {
        doAsync {
            loggedUserId = privateKey?.value?.let { repository.getAccountByPrivateKey(it).accId }!!
        }

        Thread.sleep(1000)

        inPin?.value?.let { setLoggedUser(loggedUserId, it) }
        navController.navigate(R.id.action_homeFragment_to_basicFragment)
    }

    fun setLoggedUser(accId: Long, inPinIt: String) {
        repository.setLoggedUser(accId, inPinIt)
        Log.e("LoggedUser", "done: ${accId}    ${inPinIt}")
    }
}
