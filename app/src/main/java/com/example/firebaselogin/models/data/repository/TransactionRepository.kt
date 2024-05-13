package com.example.firebaselogin.models.data.repository

import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val transactionDao: TransactionDao
) {
    fun getAllTransactions(): Flow<List<Transactions>> = transactionDao.getAllTransactions()
    suspend fun insert(transactions: Transactions) = transactionDao.insertTransaction(transactions)
    suspend fun deleteById(id: Int) { transactionDao.deleteTransactionById(id) }
    suspend fun delete(transactions: Transactions) = transactionDao.deleteTransaction(transactions)
    fun getTransactionById(id: Int): Flow<Transactions> { return transactionDao.getTransactionById(id) }
}