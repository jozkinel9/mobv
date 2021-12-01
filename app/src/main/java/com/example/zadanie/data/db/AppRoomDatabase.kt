package com.example.zadanie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zadanie.data.db.model.Account
import com.example.zadanie.data.db.model.Contact
import com.example.zadanie.data.db.model.Transaction

@Database(
    entities = [Account::class, Contact::class, Transaction::class],
    version = 5,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun appDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java, "MOBV.db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}