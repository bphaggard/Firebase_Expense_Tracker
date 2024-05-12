package com.example.firebaselogin.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.R
import com.example.firebaselogin.navigation.BottomNavigationBar
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import com.example.firebaselogin.ui.theme.blueGrad1
import com.example.firebaselogin.ui.theme.blueGrad2
import com.example.firebaselogin.ui.theme.leagueFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val mainFont = leagueFamily

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome!",
                        fontFamily = mainFont,
                        fontSize = 26.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
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
                Box(
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = "Transactions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mainFont
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessPrev() {
    FirebaseLoginTheme {
        HomeScreen(rememberNavController())
    }
}