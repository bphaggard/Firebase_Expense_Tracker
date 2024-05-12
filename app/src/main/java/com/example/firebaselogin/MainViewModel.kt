package com.example.firebaselogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaselogin.models.Transactions
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel: ViewModel() {
}