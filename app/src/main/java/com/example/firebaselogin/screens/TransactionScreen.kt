package com.example.firebaselogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.elements.TransactionCard
import com.example.firebaselogin.elements.categoryImageMap
import com.example.firebaselogin.elements.mapAmountToColor
import com.example.firebaselogin.models.charts.ExpenseChart

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
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.3f),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    //AddDefaultBarChart()
                    ExpenseChart(
                        infos = transactionList,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .align(CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
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
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ValuesConverter(viewModel: TransactionViewModel) {
    val transactionList = viewModel.allTransactions.collectAsState(initial = emptyList()).value

    
}