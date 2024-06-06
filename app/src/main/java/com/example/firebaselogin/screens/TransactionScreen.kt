package com.example.firebaselogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.parts.AddDefaultLineChart
import com.example.firebaselogin.parts.TransactionCard
import com.example.firebaselogin.parts.categoryImageMap
import com.example.firebaselogin.parts.mapAmountToColor

@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel()
){
    val transactionList = viewModel.allTransactions.collectAsState(initial = emptyList()).value

    Scaffold(
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AddDefaultLineChart()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    items(transactionList) { transaction ->
                        categoryImageMap[transaction.category]?.let {
                            Spacer(modifier = Modifier.height(16.dp))
                            TransactionCard(
                                image = it,
                                title = transaction.title,
                                amount = transaction.amount,
                                date = transaction.date,
                                color = mapAmountToColor(transaction.category),
                                onDismiss = { viewModel.deleteTransactionById(transaction.id) }
                            )
                        }
                    }
                }
            }
        }
    )
}