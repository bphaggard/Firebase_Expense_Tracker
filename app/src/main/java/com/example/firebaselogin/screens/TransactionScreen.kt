package com.example.firebaselogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.navigation.BottomNavigationBar
import com.example.firebaselogin.parts.TransactionCard
import com.example.firebaselogin.parts.categoryImageMap

@Composable
fun TransactionScreen(
    navController: NavController,
    viewModel: TransactionViewModel
){

    val transactionList by viewModel.allTransactions.collectAsState(initial = emptyList())

    Scaffold(
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ){
                items(transactionList) { transaction ->
                    categoryImageMap[transaction.category]?.let {
                        TransactionCard(
                            image = it,
                            title = transaction.title,
                            amount = transaction.amount,
                            date = transaction.date)
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
}