package com.example.firebaselogin.models.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firebaselogin.models.Summary
import com.example.firebaselogin.models.Transactions
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transactions: Transactions)

    @Update
    suspend fun updateTransaction(transactions: Transactions)

    @Delete
    suspend fun deleteTransaction(transactions: Transactions)

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: Int): Flow<Transactions>

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Transactions>>

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransactionById(id: Int)

    @Query("SELECT SUM(amount) FROM transactions WHERE category = :category")
    suspend fun getTotalByCategory(category: String): Double?

    @Query("SELECT * FROM summary WHERE id = 1")
    suspend fun getSummary(): Summary?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSummary(summary: Summary)
}