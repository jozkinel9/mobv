package com.example.zadanie.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    val accounts: LiveData<List<Account>>
        get() = repository.getAccounts()

    val inputText: MutableLiveData<String> = MutableLiveData()

// TODO   je to zatad spravene na kontrolovanie passwordu,
//  ale ani to nevim jak prehodit na druhy fragment
    fun checkUserPin() : Boolean?  = Transformations.map(accounts) {
            var check = false
            it?.let {
                for (i in it) {
                    if(i.password.equals(inputText)) {
                        check = true
                        break
                    }
                }
            }
            check
        }.value






//    //TODO: 3. urobit enkapsulaciu premennej word
//    val _word: MutableLiveData<String> = MutableLiveData()
//    val word: LiveData<String>
//        get() = _word
//
//    //TODO: 4. urobit obojsmerny binding pre edittext
//    val inputText: MutableLiveData<String> = MutableLiveData()
//
//    //TODO: 5. nahradit listener databindingom v xml
//    fun changeWord() {
//        inputText.value?.let {
//            if (it.isNotEmpty()) {
//                this._word.postValue(it)
//                inputText.postValue("")
//            }
//        }
//    }
//
//    //TODO: 6b.urobit transformaciu slova aby sa zobrazoval text "Slovo je: "
//    val transformedText: LiveData<String> = Transformations.map(inputText) {
//        "Slovo je $it"
//    }
}
