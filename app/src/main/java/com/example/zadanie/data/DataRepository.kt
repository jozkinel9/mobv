package com.example.zadanie.data

import androidx.lifecycle.LiveData
import com.example.zadanie.data.db.LocalCache
import com.example.zadanie.data.db.model.WordItem

/**
 * Repository class that works with local and remote data sources.
 */
class DataRepository private constructor(
    private val cache: LocalCache
) {

    companion object {
        const val TAG = "DataRepository"
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(cache: LocalCache): DataRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: DataRepository(cache).also { INSTANCE = it }
            }
    }

    fun getWords(): LiveData<List<WordItem>> = cache.getWords()

    suspend fun insertWord(wordItem: WordItem) {
        cache.insertWord(wordItem)
    }


}
