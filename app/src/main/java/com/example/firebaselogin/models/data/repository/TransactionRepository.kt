package com.example.firebaselogin.models.data.repository

import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository (private val transactionDao: TransactionDao) {

    suspend fun insertTransaction(transactions: Transactions) {
        transactionDao.insertTransaction(transactions)
    }

    suspend fun deleteTransaction(transactions: Transactions) {
        transactionDao.deleteTransaction(transactions)
    }

    suspend fun deleteTransactionById(id: Int) {
        transactionDao.deleteTransactionById(id)
    }

    fun getTransactionById(id: Int): Flow<Transactions> {
        return transactionDao.getTransactionById(id)
    }

    fun getAllTransactions(): Flow<List<Transactions>> {
        return transactionDao.getAllTransactions()
    }

    fun getTotalIncome(): Flow<Double> {
        return transactionDao.getTotalIncome()
    }

    fun getTotalExpense(): Flow<Double> {
        return transactionDao.getTotalExpense()
    }
}