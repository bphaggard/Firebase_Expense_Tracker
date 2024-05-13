package com.example.firebaselogin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.R
import com.example.firebaselogin.navigation.BottomNavigationBar
import com.example.firebaselogin.parts.TransactionCard
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme

@Composable
fun TransactionScreen(navController: NavController){
    Scaffold(
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    TransactionCard(
                        image = R.drawable.shop,
                        title = "Jeans",
                        amount = 200.0,
                        date = "20.04.2024"
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
fun TransactionScreenPreview() {
    FirebaseLoginTheme {
        TransactionScreen(rememberNavController())
    }
}