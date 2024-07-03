package com.example.firebaselogin.models.data.repository

import com.example.firebaselogin.models.Summary
import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository (private val transactionDao: TransactionDao) {

    suspend fun insertTransaction(transactions: Transactions) {
        transactionDao.insertTransaction(transactions)
    }

    suspend fun deleteTransactionById(id: Int) {
        transactionDao.deleteTransactionById(id)
    }

    fun getAllTransactions(): Flow<List<Transactions>> {
        return transactionDao.getAllTransactions()
    }

    suspend fun insertSummary(summary: Summary) {
        transactionDao.insertSummary(summary)
    }

    suspend fun getTotalByCategory(category: String): Double? {
        return transactionDao.getTotalByCategory(category)
    }
}