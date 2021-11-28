package com.example.zadanie.ui.viewModels


import androidx.lifecycle.*
import com.example.zadanie.data.DataRepository
import com.example.zadanie.data.db.model.WordItem
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DataRepository) : ViewModel() {

    val words: LiveData<List<WordItem>>
        get() = repository.getWords()

    //len funkcia na vypisanie textu
    val wordAsText: LiveData<String> = Transformations.map(words) {
        var text = ""
        it?.let {
            it.forEach { text += "$it, " }
        }
        text
    }

    val inputText: MutableLiveData<String> = MutableLiveData()

    fun insertWord() {
        inputText.value?.let {
            if (it.isNotEmpty()) {
                viewModelScope.launch { repository.insertWord(WordItem(it)) }
                inputText.postValue("")
            }
        }
    }
}
