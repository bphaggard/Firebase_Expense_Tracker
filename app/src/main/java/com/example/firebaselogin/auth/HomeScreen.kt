package com.example.firebaselogin.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.firebaselogin.R
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.elements.categoryImageMap
import com.example.firebaselogin.elements.getFormattedDate
import com.example.firebaselogin.elements.mapAmountToColor
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import com.example.firebaselogin.ui.theme.blueGrad1
import com.example.firebaselogin.ui.theme.blueGrad2
import com.example.firebaselogin.ui.theme.leagueFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val mainFont = leagueFamily
    val transactionList = viewModel.allTransactions.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome!",
                        fontFamily = mainFont,
                        fontSize = 26.sp,
                        modifier = Modifier.padding(start = 10.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(200.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        blueGrad1,
                                        blueGrad2
                                    )
                                )
                            )
                    ) {
                        Text(
                            text = "Available Balance",
                            fontFamily = mainFont
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "$30000.00",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = mainFont
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(horizontal = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_upward),
                                    contentDescription = null,
                                    tint = Color.Green)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Income",
                                        fontSize = 14.sp,
                                        fontFamily = mainFont
                                    )
                                    Text(
                                        text = "$10000.00",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = mainFont
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_downward),
                                    contentDescription = null,
                                    tint = Color.Red)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Expense",
                                        fontSize = 14.sp,
                                        fontFamily = mainFont
                                    )
                                    Text(
                                        text = "$8000.00",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = mainFont
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = "Recent transactions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mainFont
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(transactionList) {transaction ->
                            categoryImageMap[transaction.category]?.let {
                                ItemCard(
                                    image = it,
                                    title = transaction.title,
                                    amount = transaction.amount,
                                    date = getFormattedDate(transaction.date),
                                    color = mapAmountToColor(transaction.category)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ItemCard(
    image: Int,
    title: String,
    amount: String,
    date: String,
    color: Color
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.width(14.dp))
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            modifier = Modifier
                .padding(bottom = 47.dp)
                .fillMaxWidth(0.5f),
            text = title,
            fontSize = 22.sp,
            fontFamily = leagueFamily,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$amount Kč",
                color = color,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = date,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessPrev() {
    FirebaseLoginTheme {
        HomeScreen(rememberNavController())
    }
}