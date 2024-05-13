package com.example.firebaselogin.models.di

import android.app.Application
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
class AppModule {

    @Singleton
    @Provides
    fun provideTransactionRepository(
        transactionDao: TransactionDao
    ): TransactionRepository {
        return TransactionRepository(transactionDao)
    }

    @Singleton
    @Provides
    fun provideTransactionDatabase(app: Application): TransactionDatabase {
        return TransactionDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideTransactionDao(appDatabase: TransactionDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }
}