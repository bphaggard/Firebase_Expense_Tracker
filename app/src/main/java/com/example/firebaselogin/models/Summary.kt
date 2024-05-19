package com.example.firebaselogin.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summary")
data class Summary(
    @PrimaryKey
    val id: Int = 1,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val total: Double = 0.0
)
