package com.example.firebaselogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaselogin.models.Summary
import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()
    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    private val _allTransactions = MutableStateFlow<List<Transactions>>(emptyList())
    val allTransactions: StateFlow<List<Transactions>> = _allTransactions

    init {
        viewModelScope.launch {
            repository.getAllTransactions()
                .map { transactions ->
                    transactions.sortedByDescending { parseDate(it.date) }
                }
                .collect {
                    _allTransactions.value = it
                }
        }
        calculateTotals()
    }

    private fun parseDate(dateString: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.parse(dateString) ?: Date(0) // Default to epoch if parsing fails
    }
    private val _totalIncome = MutableStateFlow<Double>(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome
    private val _totalExpense = MutableStateFlow<Double>(0.0)
    val totalExpense: StateFlow<Double> = _totalExpense
    private val _total = MutableStateFlow<Double>(0.0)
    val total: StateFlow<Double> = _total

    private val expenseCategories = listOf("Food", "Entertainment", "Shopping", "Travel", "Other", "Expense", "Groceries")

    private fun calculateTotals() {
        viewModelScope.launch {
            val income = repository.getTotalByCategory("Income") ?: 0.0
            val expense = expenseCategories.sumOf { category ->
                repository.getTotalByCategory(category) ?: 0.0
            }
            val totalValue = income - expense
            val summary = Summary(totalIncome = income, totalExpense = expense, total = totalValue)
            repository.insertSummary(summary)
            updateTotals(summary)
        }
    }

    private fun updateTotals(summary: Summary) {
        _totalIncome.value = summary.totalIncome
        _totalExpense.value = summary.totalExpense
        _total.value = summary.total
    }

    fun insertTransaction(transactions: Transactions) {
        viewModelScope.launch {
            repository.insertTransaction(transactions)
        }
    }

    fun deleteTransactionById(id: Int) {
        viewModelScope.launch {
            repository.deleteTransactionById(id)
        }
    }
}