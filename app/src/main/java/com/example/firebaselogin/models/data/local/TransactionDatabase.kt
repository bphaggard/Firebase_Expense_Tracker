package com.example.firebaselogin.models.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebaselogin.models.Transactions

@Database(entities = [Transactions::class], version = 1)
abstract class TransactionDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        private var INSTANCE: TransactionDatabase? = null
        fun getInstance(context: Context): TransactionDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, TransactionDatabase::class.java, "transaction.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as TransactionDatabase
        }
    }
}