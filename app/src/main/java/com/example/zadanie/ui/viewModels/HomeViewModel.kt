package com.example.zadanie.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.databinding.BasicFragmentBinding
import com.example.zadanie.databinding.FragmentHomeBinding
import com.example.zadanie.ui.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeViewModel(private val repository: DataRepository) : ViewModel() {
    val accounts: LiveData<List<Account>>
        get() = repository.getAccounts()

    val inputText: MutableLiveData<String> = MutableLiveData()
    val wordAsText: LiveData<MutableList<String>> = Transformations.map(accounts) {
        val text: MutableList<String> = mutableListOf()
        it?.let {
            it.forEach{text.add(it.password)}
//                it.forEach { text += "${it.accId}, ${it.password}, ${it.private_key} \n" }
        }
        text
    }
// TODO   je to zatad spravene na kontrolovanie passwordu, je to picovsky spravene ale nevim jak na to inac
    fun checkUserPin(navController: NavController) {
        wordAsText.value?.let { wat ->
            if (wat.isNotEmpty()) {
                inputText.value?.let { it ->
                    if (it.isNotEmpty()) {
                        for (i in wat) {
                            if (i == it) {
                                navController.navigate(R.id.action_homeFragment_to_basicFragment)
                                break
                            }
                        }
                    }
                }
            }
        }
}





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
