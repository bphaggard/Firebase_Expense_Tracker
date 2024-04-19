package com.example.firebaselogin.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.FbViewModel
import com.example.firebaselogin.R
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme

@Composable
fun SuccessScreen(navController : NavController , viewModel : FbViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lu),
            contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessPrev() {
    FirebaseLoginTheme {
        SuccessScreen(
            navController = rememberNavController() ,
            viewModel = viewModel()
        )
    }
}