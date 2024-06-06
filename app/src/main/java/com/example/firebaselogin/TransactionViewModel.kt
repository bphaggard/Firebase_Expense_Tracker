package com.example.firebaselogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Transaction
import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    //val allTransactions: Flow<List<Transactions>> = repository.getAllTransactions()
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
    }

    private fun parseDate(dateString: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.parse(dateString) ?: Date(0) // Default to epoch if parsing fails
    }

    val totalIncome: StateFlow<Double> = repository.getTotalIncome()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    val totalExpense: StateFlow<Double> = repository.getTotalExpense()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    val total: StateFlow<Double> = combine(totalIncome, totalExpense) { income, expense ->
        income - expense
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)
    fun insertTransaction(transactions: Transactions) {
        viewModelScope.launch {
            repository.insertTransaction(transactions)
        }
    }

    fun deleteTransaction(transactions: Transactions) {
        viewModelScope.launch {
            repository.deleteTransaction(transactions)
        }
    }

    fun deleteTransactionById(id: Int) {
        viewModelScope.launch {
            repository.deleteTransactionById(id)
        }
    }
}