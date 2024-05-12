package com.example.firebaselogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.auth.LoginScreen
import com.example.firebaselogin.auth.MainScreen
import com.example.firebaselogin.auth.SignUpScreen
import com.example.firebaselogin.auth.HomeScreen
import com.example.firebaselogin.main.NotificationMessage
import com.example.firebaselogin.screens.AddScreen
import com.example.firebaselogin.screens.TransactionScreen
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(this.window, false)
        setContent {
            window.statusBarColor = getColor(R.color.black)
            window.navigationBarColor = getColor(R.color.black)
            FirebaseLoginTheme {
                AuthenticationApp()
            }
        }
    }
}

sealed class DestinationScreen(val route: String) {
    object Main: DestinationScreen("Main")
    object SignUp: DestinationScreen("SignUp")
    object Login: DestinationScreen("Login")
    object Home: DestinationScreen("Home")
    object Add: DestinationScreen("Add")
    object Transactions: DestinationScreen("Transactions")
}

@Composable
fun AuthenticationApp() {
    val viewModel = hiltViewModel<FbViewModel>()
    val navController = rememberNavController()

    NotificationMessage(viewModel)

    NavHost(navController =  navController, startDestination = DestinationScreen.Main.route) {
        composable(DestinationScreen.Main.route) {
            MainScreen(navController)
        }
        composable(DestinationScreen.SignUp.route) {
            SignUpScreen(navController, viewModel)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController, viewModel)
        }
        composable(DestinationScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(DestinationScreen.Add.route) {
            AddScreen(navController)
        }
        composable(DestinationScreen.Transactions.route) {
            TransactionScreen(navController)
        }
    }
}