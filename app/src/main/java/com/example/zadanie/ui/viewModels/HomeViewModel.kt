package com.example.zadanie.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    //TODO: 3. urobit enkapsulaciu premennej word
    val _word: MutableLiveData<String> = MutableLiveData()
    val word: LiveData<String>
        get() = _word

    //TODO: 4. urobit obojsmerny binding pre edittext
    val inputText: MutableLiveData<String> = MutableLiveData()

    //TODO: 5. nahradit listener databindingom v xml
    fun changeWord() {
        inputText.value?.let {
            if (it.isNotEmpty()) {
                this._word.postValue(it)
                inputText.postValue("")
            }
        }
    }

    //TODO: 6b.urobit transformaciu slova aby sa zobrazoval text "Slovo je: "
    val transformedText: LiveData<String> = Transformations.map(inputText) {
        "Slovo je $it"
    }
}
