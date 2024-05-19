package com.example.firebaselogin.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.DestinationScreen
import com.example.firebaselogin.R
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import com.example.firebaselogin.ui.theme.leagueFamily

@Composable
fun MainScreen(navController : NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(
                id = if (!isSystemInDarkTheme()) R.drawable.coins
                else R.drawable.coins_light
            ),
            contentDescription = null,
            Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = "Expense Tracker",
            fontFamily = leagueFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Your personal finance assistant",
            Modifier.alpha(0.4f),
            fontFamily = leagueFamily,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                navController.navigate(DestinationScreen.SignUp.route)
            },
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp),
        ) {
            Text(
                    text = "Sign Up",
                    fontSize = 22.sp,
                    fontFamily = leagueFamily,
                    fontWeight = FontWeight.Bold
                )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(DestinationScreen.Login.route)
            },
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp),
        ) {
            Text(
                text = "Log In",
                fontSize = 22.sp,
                fontFamily = leagueFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPrev() {
    FirebaseLoginTheme() {
        MainScreen(navController = rememberNavController())
    }
}