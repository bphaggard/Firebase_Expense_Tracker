package com.example.firebaselogin.models.di

import android.app.Application
import androidx.room.Room
import com.example.firebaselogin.models.data.local.TransactionDao
import com.example.firebaselogin.models.data.local.TransactionDatabase
import com.example.firebaselogin.models.data.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTransactionRepository(dao: TransactionDao): TransactionRepository {
        return TransactionRepository(dao)
    }

    @Singleton
    @Provides
    fun provideTransactionDatabase(app: Application): TransactionDatabase {
        return Room.databaseBuilder(
            app,
            TransactionDatabase::class.java,
            "transaction_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTransactionDao(database: TransactionDatabase): TransactionDao {
        return database.transactionDao()
    }
}