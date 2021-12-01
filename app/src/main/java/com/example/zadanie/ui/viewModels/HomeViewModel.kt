package com.example.zadanie.ui.viewModels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.zadanie.R
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.LoggedUser
import com.example.zadanie.data.db.model.Account

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    val accounts: LiveData<List<Account>>
        get() = repository.getAccounts()

//    nahradit za privateKeyForTesting
//    val inPrivateKey: MutableLiveData<String> = MutableLiveData()

    lateinit var account: LiveData<Account>
    val inPin: MutableLiveData<String> = MutableLiveData()

    // all privatekys from db
    val dbPrivateKeys: LiveData<MutableList<String>> = Transformations.map(accounts) {
        val text: MutableList<String> = mutableListOf()
        it?.let {
            it.forEach { text.add(it.private_key) }
        }
        text
    }

    //    only for testing, vymenit za inPrivateKey
    val privateKeyForTesting: MutableLiveData<String> = Transformations.map(accounts) {
        var text = ""
        it?.let {
            text = it.get(2).private_key
        }
        text
    } as MutableLiveData<String>

    // check if private key exist in db, TODO ulozit accId a PIN do singleton classy alebo db
    fun checkUserPin(navController: NavController) {

        inPin.value?.let { inPinIt ->
            dbPrivateKeys.value?.let { dbPrivateKeysIt ->
                if (dbPrivateKeysIt.isNotEmpty()) {
                    privateKeyForTesting.value?.let { inPrivateKeyIt ->
                        if (inPrivateKeyIt.isNotEmpty()) {
                            for (i in 0..dbPrivateKeysIt.size) {
                                if (dbPrivateKeysIt[i] == inPrivateKeyIt) {
                                    navController.navigate(R.id.action_homeFragment_to_basicFragment)
                                    setLoggedUser(i.toLong(), inPinIt)
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //TODO kurva zle to je... treba ukladat do getLogUser(accId a PIN)
    fun setLoggedUser(accId: Long, inPinIt: String) {
        repository.setLoggedUser(accId+1, inPinIt)
        val bla: LoggedUser = repository.getLoggedUser()
        Log.e("LoggedUser", "done: ${bla.getAccId()}    ${bla.getPin()}")

    }
}




//    val _word: MutableLiveData<String> = MutableLiveData()
//    val word: LiveData<String>
//        get() = _word
//
//    val inputText: MutableLiveData<String> = MutableLiveData()
//
//    fun changeWord() {
//        inputText.value?.let {
//            if (it.isNotEmpty()) {
//                this._word.postValue(it)
//                inputText.postValue("")
//            }
//        }
//    }
//
//    val transformedText: LiveData<String> = Transformations.map(inputText) {
//        "Slovo je $it"
//    }

