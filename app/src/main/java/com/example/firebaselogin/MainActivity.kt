package com.example.firebaselogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.auth.HomeScreen
import com.example.firebaselogin.auth.LoginScreen
import com.example.firebaselogin.auth.MainScreen
import com.example.firebaselogin.auth.SignUpScreen
import com.example.firebaselogin.main.NotificationMessage
import com.example.firebaselogin.navigation.AppScaffold
import com.example.firebaselogin.screens.AddScreen
import com.example.firebaselogin.screens.TransactionScreen
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(this.window, false)
        setContent {
            window.statusBarColor = getColor(R.color.black)
            window.navigationBarColor = getColor(R.color.black)
            FirebaseLoginTheme {
                AppScaffold(transactionViewModel)
            }
        }
    }
}

//sealed class DestinationScreen(val route: String, val icon: Int, val label: String) {
//    data object Main: DestinationScreen("main", 0, "Main")
//    data object SignUp: DestinationScreen("signup", 0, "SignUp")
//    data object Login: DestinationScreen("login", 0, "Login")
//    data object Home: DestinationScreen("home", R.drawable.rounded_home, "Home")
//    data object Add: DestinationScreen("add", R.drawable.round_add_circle, "Add")
//    data object Transactions: DestinationScreen("transactions", R.drawable.rounded_compare_arrows, "Transactions")
//}