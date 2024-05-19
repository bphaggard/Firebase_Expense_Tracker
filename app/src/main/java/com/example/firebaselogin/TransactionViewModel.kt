package com.example.firebaselogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.models.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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

    val allTransactions: Flow<List<Transactions>> = repository.getAllTransactions()
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

    fun getTransactionById(id: Int): Flow<Transactions> {
        return repository.getTransactionById(id)
    }

    private val _inputAmount = MutableStateFlow("")
    val inputAmount: StateFlow<String> = _inputAmount.asStateFlow()
    fun setInputAmount(amount: String) {
        _inputAmount.value = amount
    }

    private val _inputTitle = MutableStateFlow("")
    val inputTitle: StateFlow<String> = _inputTitle.asStateFlow()
    fun setInputTitle(title: String) {
        _inputTitle.value = title
    }

    private val _inputCategory = MutableStateFlow("")
    val inputCategory: StateFlow<String> = _inputCategory.asStateFlow()
    fun setInputCategory(category: String) {
        _inputCategory.value = category
    }
}